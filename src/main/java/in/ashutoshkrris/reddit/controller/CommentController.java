package in.ashutoshkrris.reddit.controller;

import in.ashutoshkrris.reddit.dto.request.CommentRequestDto;
import in.ashutoshkrris.reddit.dto.response.CommentResponseDto;
import in.ashutoshkrris.reddit.service.impl.CommentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequest) {
        CommentResponseDto comment = commentService.save(commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsForPost(@PathVariable Long postId) {
        List<CommentResponseDto> comments = commentService.getAllForPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsForUser(@PathVariable String username) {
        List<CommentResponseDto> comments = commentService.getAllForUser(username);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
