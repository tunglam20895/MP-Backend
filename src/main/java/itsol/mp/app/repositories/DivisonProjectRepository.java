package itsol.mp.app.repositories;

import itsol.mp.app.entities.DivisonProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisonProjectRepository extends JpaRepository<DivisonProject,Long> {
}
