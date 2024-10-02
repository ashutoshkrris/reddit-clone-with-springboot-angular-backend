package in.ashutoshkrris.reddit.service.impl;

import in.ashutoshkrris.reddit.constants.VoteType;
import in.ashutoshkrris.reddit.dto.request.VoteRequestDto;
import in.ashutoshkrris.reddit.exceptions.PostNotFoundException;
import in.ashutoshkrris.reddit.exceptions.RedditException;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.User;
import in.ashutoshkrris.reddit.model.Vote;
import in.ashutoshkrris.reddit.repository.PostRepository;
import in.ashutoshkrris.reddit.repository.VoteRepository;
import in.ashutoshkrris.reddit.service.AuthenticationService;
import in.ashutoshkrris.reddit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public void vote(VoteRequestDto voteRequest) {
        Long postId = voteRequest.getPostId();
        VoteType voteType = voteRequest.getVoteType();
        User currentUser = authenticationService.getCurrentUser();

        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new PostNotFoundException("No post found with id: " + postId, HttpStatus.NOT_FOUND));
        Optional<Vote> userVoteOnPost = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, currentUser);
        if (userVoteOnPost.isPresent()) {
            if (userVoteOnPost.get().getVoteType().equals(voteType)) {
                throw new RedditException(String.format("You have already %sd on this post.", voteType.name().toLowerCase()), HttpStatus.BAD_REQUEST);
            }
        }
        Integer voteCount = post.getVoteCount() == null ? 0 : post.getVoteCount();
        post.setVoteCount(voteCount + voteType.getDirection());
        voteRepository.save(Vote.builder().voteType(voteType).post(post).user(currentUser).build());
        postRepository.save(post);
    }
}
