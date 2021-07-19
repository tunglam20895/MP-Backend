package itsol.mp.app.repositories;

import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.entities.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects,Long> {
    Projects findByName(String name);

    @Query("select new itsol.mp.app.dto.ProjectDTO(p.id, p.name,p.description," +
            "p.dateStarted,p.dateEnd,u.firstName,u.lastName)" +
            " from Users u inner join ProjectUser pu on u.id = pu.projectUser \n" +
            "    inner join Projects p on pu.userProject = p.id where u.role = 'ROLE_PM'")
    List<ProjectDTO> getallProject();

    @Query("select new itsol.mp.app.dto.ProjectDTO(p.id, p.name,p.description," +
            "p.dateStarted,p.dateEnd,u.firstName,u.lastName)" +
            " from Users u inner join ProjectUser pu on u.id = pu.projectUser \n" +
            "    inner join Projects p on pu.userProject = p.id where u.role = 'ROLE_PM' and u.username = :p_username")
    List<ProjectDTO> getProjectByPM(@Param("p_username")String username);

}
