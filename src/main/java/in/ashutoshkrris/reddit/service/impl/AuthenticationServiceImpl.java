package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.dto.NotificationEmail;
import in.ashutoshkrris.reddit.dto.request.LoginRequestDto;
import in.ashutoshkrris.reddit.dto.request.RefreshTokenRequestDto;
import in.ashutoshkrris.reddit.dto.request.SignUpRequestDto;
import in.ashutoshkrris.reddit.dto.response.AuthenticationResponseDto;
import in.ashutoshkrris.reddit.exceptions.RedditException;
import in.ashutoshkrris.reddit.model.User;
import in.ashutoshkrris.reddit.model.VerificationToken;
import in.ashutoshkrris.reddit.repository.UserRepository;
import in.ashutoshkrris.reddit.repository.VerificationTokenRepository;
import in.ashutoshkrris.reddit.security.JWTProvider;
import in.ashutoshkrris.reddit.service.AuthenticationService;
import in.ashutoshkrris.reddit.service.MailService;
import in.ashutoshkrris.reddit.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        userRepository.save(user);

        String verificationToken = generateVerificationToken(user);
        NotificationEmail notificationEmail = NotificationEmail.builder()
                .subject("Please activate your Reddit account")
                .recipient(signUpRequest.getEmail())
                .body("Thank you for signing up on Reddit. Please click on the link to activate your account: http://localhost:8080/api/auth/verify/" + verificationToken)
                .build();
        mailService.sendMail(notificationEmail);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Override
    @Transactional(readOnly = true)
    public void verifyAccount(String token) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(token);
        optionalVerificationToken.orElseThrow(() -> new RedditException("Invalid token", HttpStatus.BAD_REQUEST));
        VerificationToken verificationToken = optionalVerificationToken.get();
        User user = verificationToken.getUser();
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponseDto login(LoginRequestDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return AuthenticationResponseDto.builder()
                .authenticationToken(token)
                .username(loginRequest.getUsername())
                .refreshToken(refreshTokenService.generateToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }

    @Override
    public AuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequest) {
        refreshTokenService.validateToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponseDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
