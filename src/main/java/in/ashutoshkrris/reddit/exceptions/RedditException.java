package in.ashutoshkrris.reddit.exceptions;

public class RedditException extends RuntimeException {
    public RedditException(String message) {
        super(message);
    }

    public RedditException(String message, Exception e) {
        super(message, e);
    }
}
