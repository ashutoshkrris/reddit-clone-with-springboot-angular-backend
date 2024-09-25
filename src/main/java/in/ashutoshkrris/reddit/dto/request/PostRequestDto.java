package in.ashutoshkrris.reddit.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {

    private String subRedditName;
    private String postName;
    private String url;
    private String description;

}
