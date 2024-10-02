package in.ashutoshkrris.reddit.exceptions;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends RedditException {

    public PostNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
