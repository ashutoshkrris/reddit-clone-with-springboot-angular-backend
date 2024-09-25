package in.ashutoshkrris.reddit.repository;

import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.SubReddit;
import in.ashutoshkrris.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostId(Long postId);

    List<Post> findAllBySubReddit(SubReddit subReddit);

    List<Post> findAllByUser(User user);

}