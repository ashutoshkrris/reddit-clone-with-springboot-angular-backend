package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.exceptions.RedditException;
import in.ashutoshkrris.reddit.model.RefreshToken;
import in.ashutoshkrris.reddit.model.RefreshTokenRepository;
import in.ashutoshkrris.reddit.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateToken(String token) {
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new RedditException("Invalid refresh token", HttpStatus.BAD_REQUEST));
    }

    @Override
    @Transactional
    public void deleteToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

}
