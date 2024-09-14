package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.SignUpRequestDto;

public interface AuthenticationService {

    void signUp(SignUpRequestDto signUpRequest);

}
