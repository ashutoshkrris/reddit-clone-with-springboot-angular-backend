package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.request.CommentRequestDto;
import in.ashutoshkrris.reddit.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto save(CommentRequestDto commentRequest);

    List<CommentResponseDto> getAllForPost(Long postId);

    List<CommentResponseDto> getAllForUser(String username);
}
