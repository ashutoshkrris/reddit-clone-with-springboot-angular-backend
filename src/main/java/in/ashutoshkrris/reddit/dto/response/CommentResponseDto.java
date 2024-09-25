package in.ashutoshkrris.reddit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private Long postId;
    private String text;
    private String username;
    private Long createdAt;

}
