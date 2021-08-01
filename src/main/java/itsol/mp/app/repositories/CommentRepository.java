package itsol.mp.app.repositories;

import itsol.mp.app.entities.Comments;
import itsol.mp.app.entities.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByIssueComment(Issues issues);

    void deleteAllByIssueComment(Issues issues);
}
