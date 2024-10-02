package in.ashutoshkrris.reddit.dto.request;

import in.ashutoshkrris.reddit.constants.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDto {

    private VoteType voteType;
    private Long postId;

}
