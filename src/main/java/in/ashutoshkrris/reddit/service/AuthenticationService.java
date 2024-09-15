package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.AuthenticationResponseDto;
import in.ashutoshkrris.reddit.dto.LoginRequestDto;
import in.ashutoshkrris.reddit.dto.SignUpRequestDto;

public interface AuthenticationService {

    void signUp(SignUpRequestDto signUpRequest);

    void verifyAccount(String token);

    AuthenticationResponseDto login(LoginRequestDto loginRequest);
}
