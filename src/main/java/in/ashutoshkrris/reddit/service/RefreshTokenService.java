package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.model.RefreshToken;

public interface RefreshTokenService {

    public RefreshToken generateToken();

    public void validateToken(String token);

    public void deleteToken(String token);
}
