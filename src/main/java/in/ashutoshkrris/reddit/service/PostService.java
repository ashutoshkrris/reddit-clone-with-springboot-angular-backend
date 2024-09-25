package in.ashutoshkrris.reddit.service;

import in.ashutoshkrris.reddit.dto.request.PostRequestDto;
import in.ashutoshkrris.reddit.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto save(PostRequestDto postRequest);

    List<PostResponseDto> getAll();

    PostResponseDto getById(Long postId);

    List<PostResponseDto> getBySubRedditId(Long subRedditId);

    List<PostResponseDto> getByUsername(String username);
}
