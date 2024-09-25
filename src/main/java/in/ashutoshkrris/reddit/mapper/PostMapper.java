package in.ashutoshkrris.reddit.mapper;

import in.ashutoshkrris.reddit.dto.request.PostRequestDto;
import in.ashutoshkrris.reddit.dto.response.PostResponseDto;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.SubReddit;
import in.ashutoshkrris.reddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "user", source = "user")
    public abstract Post mapRequestDtoToPost(PostRequestDto postRequest, SubReddit subReddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subRedditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.username")
    public abstract PostResponseDto mapPostToResponseDto(Post post);

}
