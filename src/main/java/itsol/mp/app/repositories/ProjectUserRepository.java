package itsol.mp.app.repositories;

import itsol.mp.app.entities.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser,Long> {
//        void deleteById( Long id);

    ProjectUser findProjectUserById(long id);
}
