package in.ashutoshkrris.reddit.controller;

import in.ashutoshkrris.reddit.dto.request.LoginRequestDto;
import in.ashutoshkrris.reddit.dto.request.RefreshTokenRequestDto;
import in.ashutoshkrris.reddit.dto.request.SignUpRequestDto;
import in.ashutoshkrris.reddit.dto.response.AuthenticationResponseDto;
import in.ashutoshkrris.reddit.service.RefreshTokenService;
import in.ashutoshkrris.reddit.service.impl.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        authenticationService.signUp(signUpRequest);
        return new ResponseEntity<>("Sign up successful", HttpStatus.OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authenticationService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        AuthenticationResponseDto authenticationResponse = authenticationService.login(loginRequest);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponseDto refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        return authenticationService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequest) {
        refreshTokenService.deleteToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!");
    }

}
