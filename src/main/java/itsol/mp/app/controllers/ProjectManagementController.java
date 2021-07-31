package itsol.mp.app.controllers;

import itsol.mp.app.dto.AddProjectDTO;
import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.dto.UserProjectDTO;
import itsol.mp.app.entities.ProjectUser;
import itsol.mp.app.entities.Projects;
import itsol.mp.app.entities.Users;
import itsol.mp.app.services.ProjectService;
import itsol.mp.app.services.ProjectUserService;
import itsol.mp.app.services.UserService;
import itsol.mp.app.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectManagementController {

    public static final Logger logger = LoggerFactory.getLogger(ProjectManagementController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectUserService projectUserService;

    @GetMapping("/list/{username}")
    public List<ProjectDTO> getProject(@PathVariable String username) {
        if (projectService.getProjectByPM(username).size() > 0) {
            return projectService.getProjectByPM(username);
        } else {
            return projectService.getListProject();
        }
    }

    @GetMapping("/project-user/{id}")
    public ResponseEntity<List<?>> getUserInProject(@PathVariable long id) {
        return new ResponseEntity<>(
                projectService.getUserProject(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete-user-project/{id}")
    public void deleteUserProject(@PathVariable long id) {
        ProjectUser projectUser = projectUserService.findById(id);
        projectUserService.deletePro(projectUser);
    }

    @Autowired
    private UserService userService;

    @PostMapping("/add-user/{id}")
    public ResponseEntity<?> addUser(@RequestBody String username, @PathVariable long id) {
        Users users = userService.findUserByUsernameAndRole(username);
        if (users == null) {
            return new ResponseEntity(
                    new CustomErrorType("User " + username + " không hợp lệ"), HttpStatus.CONFLICT
            );
        }
        Projects projects = projectService.findById(id);
     if(projectUserService.findByMemberIdAndProId(users,projects) != null){
         return new ResponseEntity(
                 new CustomErrorType("User " + username + " đã tồn tại"), HttpStatus.CONFLICT
         );
     }
        ProjectUser projectUser = new ProjectUser();
        projectUser.setProjectUser(users);
        projectUser.setUserProject(projects);
        projectUser.setIsPM((long) 0);
        return new ResponseEntity(
                projectUserService.addUserProject(projectUser), HttpStatus.CREATED
        );
    }

    @GetMapping("/get-transfer-project/{id}")
    public ResponseEntity<List<?>> getTransferProject(@PathVariable long id) {
        return new ResponseEntity<>(
                projectService.getTransferProject(id), HttpStatus.OK);
    }

    @PostMapping( "/transfer-user/{idNewProject}")
    public ResponseEntity<?> transferUser(
            @RequestBody UserProjectDTO transferUser, @PathVariable long idNewProject) {
        ProjectUser pu = projectUserService.findById(transferUser.getId());

        ProjectUser newPu = new ProjectUser();
        Users users = userService.findUserByUsername(transferUser.getUsername());
        newPu.setProjectUser(users);
        Projects projects = projectService.findById(idNewProject);
        newPu.setUserProject(projects);
        newPu.setIsPM((long) 0);

        if (projectUserService.findByMemberIdAndProId(users, projectService.findById(idNewProject)) == null) {
            ProjectUser addNewPu = projectUserService.addUserProject(newPu);
            projectUserService.deletePro(pu);
            return new ResponseEntity<ProjectUser>(
                    addNewPu, HttpStatus.CREATED
            );
        }
        return new ResponseEntity(new CustomErrorType("Lỗi"),HttpStatus.BAD_REQUEST);
    }

    Date date = new Date();
    // tạo project
    @PostMapping("/add-project")
    public ResponseEntity<?> addNewProject(@RequestBody AddProjectDTO newProject){
        System.out.println(newProject.getUsername());

        Projects projects = new Projects();
        projects.setName(newProject.getName());
        projects.setDateStarted(date);
        projects.setDateEnd(newProject.getDateEnd());
        projects.setDescription(newProject.getDescriptions());
        projects.setStatus((long)1);

        Users users = userService.findUserByUsername(newProject.getUsername());


        if(users == null){
            return new ResponseEntity(
                    new CustomErrorType("Dự án không có PM"),HttpStatus.BAD_REQUEST
            );
        }
        Projects pro = projectService.addProject(projects);
        ProjectUser projectUser = new ProjectUser();
        projectUser.setIsPM((long) 1);
        projectUser.setUserProject(pro);
        projectUser.setProjectUser(users);
        ProjectUser pu = projectUserService.addUserProject(projectUser);
        return new ResponseEntity(
          pro,HttpStatus.CREATED
        );
    }

    @GetMapping("/PM")
    public List<String> allPM(){
        return userService.findByPm();
    }

    //test
    @GetMapping("/a")
    public List<UserProjectDTO> get() {
        return projectService.getUserProject((long) 1);
    }
}
