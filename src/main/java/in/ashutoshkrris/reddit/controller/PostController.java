package in.ashutoshkrris.reddit.controller;

import in.ashutoshkrris.reddit.dto.request.PostRequestDto;
import in.ashutoshkrris.reddit.dto.response.PostResponseDto;
import in.ashutoshkrris.reddit.exceptions.PostNotFoundException;
import in.ashutoshkrris.reddit.service.impl.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private final PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequest) {
        PostResponseDto savedPost = postService.save(postRequest);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) throws PostNotFoundException {
        PostResponseDto post = postService.getById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/subreddit/{subRedditId}")
    public ResponseEntity<List<PostResponseDto>> getAllPostsForSubReddit(@PathVariable Long subRedditId) {
        List<PostResponseDto> posts = postService.getBySubRedditId(subRedditId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponseDto>> getAllPostsForUser(@PathVariable String username) {
        List<PostResponseDto> posts = postService.getByUsername(username);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
