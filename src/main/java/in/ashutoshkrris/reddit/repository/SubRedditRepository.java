package in.ashutoshkrris.reddit.repository;

import in.ashutoshkrris.reddit.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {

    Optional<SubReddit> findBySubRedditId(Long subRedditId);

    Optional<SubReddit> findByName(String subRedditName);

}