package itsol.mp.app.repositories;

import itsol.mp.app.entities.Divisons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisonRepository extends JpaRepository<Divisons,Long> {
}
