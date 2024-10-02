package in.ashutoshkrris.reddit.repository;

import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.User;
import in.ashutoshkrris.reddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);

}