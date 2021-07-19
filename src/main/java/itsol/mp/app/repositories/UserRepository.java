package itsol.mp.app.repositories;

import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Query("select role from Users where id = :p_id ")
    List<String> getRole(@Param("p_id") Long id);

    Users findByEmail(String email);

    @Query("from Users where email = :p_email")
    List<Users> findAllByEmail(@Param("p_email") String email);

    @Query("from Users where username = :p_username and email = :p_email")
    Users findByUsernameAndEmail(@Param("p_username") String username, @Param("p_email") String email);


}