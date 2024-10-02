package in.ashutoshkrris.reddit.constants;

import in.ashutoshkrris.reddit.exceptions.RedditException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum VoteType {

    UPVOTE(1),
    DOWNVOTE(-1);

    private final Integer direction;

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new RedditException("Vote not found", HttpStatus.NOT_FOUND));
    }
}
