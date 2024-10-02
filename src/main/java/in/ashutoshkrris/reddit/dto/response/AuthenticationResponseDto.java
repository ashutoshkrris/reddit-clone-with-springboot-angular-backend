package in.ashutoshkrris.reddit.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AuthenticationResponseDto {

    private String authenticationToken;
    private String username;
    private String refreshToken;
    private Instant expiresAt;

}
