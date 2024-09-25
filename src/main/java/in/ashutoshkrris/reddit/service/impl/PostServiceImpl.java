package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.dto.request.PostRequestDto;
import in.ashutoshkrris.reddit.dto.response.PostResponseDto;
import in.ashutoshkrris.reddit.exceptions.PostNotFoundException;
import in.ashutoshkrris.reddit.exceptions.SubRedditNotFoundException;
import in.ashutoshkrris.reddit.mapper.PostMapper;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.SubReddit;
import in.ashutoshkrris.reddit.model.User;
import in.ashutoshkrris.reddit.repository.PostRepository;
import in.ashutoshkrris.reddit.repository.SubRedditRepository;
import in.ashutoshkrris.reddit.repository.UserRepository;
import in.ashutoshkrris.reddit.service.AuthenticationService;
import in.ashutoshkrris.reddit.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;

    private final PostRepository postRepository;
    private final SubRedditRepository subRedditRepository;
    private final AuthenticationService authenticationService;

    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostResponseDto save(PostRequestDto postRequest) {
        SubReddit subReddit = subRedditRepository.findByName(postRequest.getSubRedditName())
                .orElseThrow(() -> new SubRedditNotFoundException(postRequest.getSubRedditName() + " was not found"));
        User currentUser = authenticationService.getCurrentUser();
        Post post = postMapper.mapRequestDtoToPost(postRequest, subReddit, currentUser);
        postRepository.save(post);
        return postMapper.mapPostToResponseDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapPostToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getById(Long postId) throws PostNotFoundException {
        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new PostNotFoundException("No post found with id: " + postId));
        return postMapper.mapPostToResponseDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getBySubRedditId(Long subRedditId) {
        SubReddit subReddit = subRedditRepository.findBySubRedditId(subRedditId).orElseThrow(() -> new SubRedditNotFoundException("No SubReddit found with id: " + subRedditId));
        List<Post> allPosts = postRepository.findAllBySubReddit(subReddit);
        return allPosts.stream()
                .map(postMapper::mapPostToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        List<Post> allPosts = postRepository.findAllByUser(user);
        return allPosts.stream()
                .map(postMapper::mapPostToResponseDto)
                .toList();
    }

}
