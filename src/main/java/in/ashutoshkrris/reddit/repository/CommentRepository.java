package in.ashutoshkrris.reddit.repository;

import in.ashutoshkrris.reddit.model.Comment;
import in.ashutoshkrris.reddit.model.Post;
import in.ashutoshkrris.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);
}