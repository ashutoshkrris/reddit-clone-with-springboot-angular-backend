package in.ashutoshkrris.reddit.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    private Long postId;
    @NotBlank
    private String text;
    private String username;

}
