package itsol.mp.app.repositories;

import itsol.mp.app.entities.Divisons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface DivisionRepository extends JpaRepository<Divisons,Long> {
    Divisons findDivisonsById(long id);
}
