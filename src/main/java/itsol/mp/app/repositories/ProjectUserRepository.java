package itsol.mp.app.repositories;

import itsol.mp.app.entities.ProjectUser;
import itsol.mp.app.entities.Projects;
import itsol.mp.app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser,Long> {
//        void deleteById( Long id);

    ProjectUser findProjectUserById(long id);

    ProjectUser findProjectUserByProjectUserAndUserProject(Users member , Projects project);
}
