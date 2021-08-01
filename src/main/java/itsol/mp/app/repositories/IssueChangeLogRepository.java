package itsol.mp.app.repositories;

import itsol.mp.app.entities.IssueChangeLog;
import itsol.mp.app.entities.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueChangeLogRepository extends JpaRepository<IssueChangeLog,Long> {

    List<IssueChangeLog> findAllByIssueChangeLog(Issues issues);

     void deleteAllByIssueChangeLog(Issues issues);
}
