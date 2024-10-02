package in.ashutoshkrris.reddit.exceptions;

import org.springframework.http.HttpStatus;

public class SubRedditNotFoundException extends RedditException {

    public SubRedditNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
