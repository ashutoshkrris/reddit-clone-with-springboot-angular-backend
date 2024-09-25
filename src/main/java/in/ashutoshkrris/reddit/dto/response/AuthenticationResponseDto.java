package in.ashutoshkrris.reddit.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {

    private String authenticationToken;
    private String username;

}
