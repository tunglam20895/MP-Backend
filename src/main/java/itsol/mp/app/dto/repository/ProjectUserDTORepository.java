//package itsol.mp.app.dto.repository;
//
//import itsol.mp.app.dto.ListProjectDTO;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProjectUserDTORepository extends JpaRepository<ListProjectDTO,Integer> {
//    @Query( "select p.id, p.name,p.description ,p.dateStarted ,p.dateEnd ,u.firstName,u.lastName from Users u inner join ProjectUser pu on u.id = pu.id\n" +
//            "    inner join Projects p on pu.id = p.id where u.role = 'ROLE_PM'")
//    List<ListProjectDTO> findAll();
//}
