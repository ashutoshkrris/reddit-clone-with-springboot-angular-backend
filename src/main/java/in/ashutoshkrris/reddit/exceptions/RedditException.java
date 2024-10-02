package in.ashutoshkrris.reddit.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RedditException extends RuntimeException {

    private HttpStatus httpStatus;

    public RedditException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public RedditException(String message) {
        super(message);
    }

    public RedditException(String message, Exception e) {
        super(message, e);
    }
}
