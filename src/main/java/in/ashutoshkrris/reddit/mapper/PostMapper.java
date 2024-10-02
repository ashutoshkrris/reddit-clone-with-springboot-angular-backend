package in.ashutoshkrris.reddit.mapper;

import in.ashutoshkrris.reddit.dto.request.PostRequestDto;
import in.ashutoshkrris.reddit.dto.response.PostResponseDto;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.SubReddit;
import in.ashutoshkrris.reddit.model.User;
import in.ashutoshkrris.reddit.repository.CommentRepository;
import in.ashutoshkrris.reddit.repository.VoteRepository;
import in.ashutoshkrris.reddit.service.AuthenticationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post mapRequestDtoToPost(PostRequestDto postRequest, SubReddit subReddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subRedditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    public abstract PostResponseDto mapPostToResponseDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findAllByPost(post).size();
    }

}
