package itsol.mp.app.repositories;

import itsol.mp.app.dto.IssueDTO;
import itsol.mp.app.entities.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issues,Long> {
    void deleteById(Long aLong);

    @Query("select new itsol.mp.app.dto.IssueDTO(i.id,i.issueProject,i.status,i.type,i.title,i.description,i.dateStrarted,i.soulution,i.progress,i.dateEnd,i.priority,u.username,u.role,i.dateUpdate,i.target) from Users u inner join ProjectUser pu on u.id = pu.projectUser inner join " +
            "Projects p on pu.userProject = p.id inner join Issues i on p.id = i.issueProject where u.role = 'ROLE_MEMBER' and u.username = :p_username" )
    List<IssueDTO> getIssuesByMember(@Param("p_username") String username);

    @Query("select new itsol.mp.app.dto.IssueDTO(i.id,i.issueProject,i.status,i.type,i.title,i.description,i.dateStrarted,i.soulution,i.progress,i.dateEnd,i.priority,u.username,u.role,i.dateUpdate,i.target) from Users u inner join ProjectUser pu on u.id = pu.projectUser inner join " +
            "Projects p on pu.userProject = p.id inner join Issues i on p.id = i.issueProject where u.role = 'ROLE_PM' and u.username = :p_username" )
    List<IssueDTO> getIssuesByPM(@Param("p_username") String username);

    @Query("select i from Projects p inner join Issues i on p.id = i.issueProject")
    List<Issues> getIssuesByAdmin();
}
