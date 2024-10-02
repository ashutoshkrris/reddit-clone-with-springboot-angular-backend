package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.dto.request.CommentRequestDto;
import in.ashutoshkrris.reddit.dto.response.CommentResponseDto;
import in.ashutoshkrris.reddit.exceptions.PostNotFoundException;
import in.ashutoshkrris.reddit.mapper.CommentMapper;
import in.ashutoshkrris.reddit.model.Comment;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.User;
import in.ashutoshkrris.reddit.repository.CommentRepository;
import in.ashutoshkrris.reddit.repository.PostRepository;
import in.ashutoshkrris.reddit.repository.UserRepository;
import in.ashutoshkrris.reddit.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentResponseDto save(CommentRequestDto commentRequest) {
        Post post = postRepository.findByPostId(commentRequest.getPostId()).orElseThrow(() -> new PostNotFoundException("No post found with id: " + commentRequest.getPostId(), HttpStatus.NOT_FOUND));
        User user = userRepository.findByUsername(commentRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException(commentRequest.getUsername()));
        Comment comment = commentMapper.mapRequestDtoToComment(commentRequest, post, user);
        commentRepository.save(comment);
        return commentMapper.mapCommentToResponseDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getAllForPost(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new PostNotFoundException("No post found with id: " + postId, HttpStatus.NOT_FOUND));
        List<Comment> allComments = commentRepository.findAllByPost(post);
        return allComments.stream()
                .map(commentMapper::mapCommentToResponseDto)
                .toList();
    }

    @Override
    public List<CommentResponseDto> getAllForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        List<Comment> allComments = commentRepository.findAllByUser(user);
        return allComments.stream()
                .map(commentMapper::mapCommentToResponseDto)
                .toList();
    }
}
