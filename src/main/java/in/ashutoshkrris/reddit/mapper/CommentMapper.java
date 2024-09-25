package in.ashutoshkrris.reddit.mapper;

import in.ashutoshkrris.reddit.dto.request.CommentRequestDto;
import in.ashutoshkrris.reddit.dto.response.CommentResponseDto;
import in.ashutoshkrris.reddit.model.Comment;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "text", source = "commentRequestDto.text")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment mapRequestDtoToComment(CommentRequestDto commentRequestDto, Post post, User user);

    @Mapping(target = "id", source = "commentId")
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentResponseDto mapCommentToResponseDto(Comment comment);

}
