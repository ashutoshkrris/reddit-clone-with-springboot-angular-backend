package in.ashutoshkrris.reddit.exceptions;

import in.ashutoshkrris.reddit.dto.response.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RedditExceptionHandler {
    @ExceptionHandler(value = RedditException.class)
    public ResponseEntity<ErrorResponseDto> exception(RedditException exception) {
        ErrorResponseDto errorResponse = ErrorResponseDto.builder().message(exception.getMessage()).httpStatus(exception.getHttpStatus()).build();
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }
}