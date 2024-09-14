package in.ashutoshkrris.reddit.controller;

import in.ashutoshkrris.reddit.dto.SignUpRequestDto;
import in.ashutoshkrris.reddit.service.impl.AuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        authenticationService.signUp(signUpRequest);
        return new ResponseEntity<>("Sign up successful", HttpStatus.OK);
    }



}
