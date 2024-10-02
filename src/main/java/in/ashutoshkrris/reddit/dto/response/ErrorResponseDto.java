package in.ashutoshkrris.reddit.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResponseDto {

    private String message;
    private HttpStatus httpStatus;

}
