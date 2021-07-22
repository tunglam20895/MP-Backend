package itsol.mp.app.controllers;

import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.dto.UserProjectDTO;
import itsol.mp.app.entities.ProjectUser;
import itsol.mp.app.entities.Projects;
import itsol.mp.app.entities.Users;
import itsol.mp.app.services.ProjectService;
import itsol.mp.app.services.ProjectUserService;
import itsol.mp.app.services.UserService;
import itsol.mp.app.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectManagementController {

    @Autowired
    private ProjectService projectService;

//    @Autowired
//    private UserRepository userRepository;

    @GetMapping("/list/{username}")
    public List<ProjectDTO> getProject(@PathVariable String username) {
        if (projectService.getProjectByPM(username).size() > 0) {
            return projectService.getProjectByPM(username);
        } else {
            return projectService.getListProject();
        }
    }

    @GetMapping("/project-user/{id}")
    public List<UserProjectDTO> getUserInProject(@PathVariable long id) {
        return projectService.getUserProject(id);
    }

    @Autowired
    protected ProjectUserService projectUserService;

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
        System.out.println(transferUser.getUsername());
        ProjectUser pu = projectUserService.findById(transferUser.getId());


        ProjectUser newPu = new ProjectUser();
        Users users = userService.findUserByUsername(transferUser.getUsername());
        newPu.setProjectUser(users);
        Projects projects = projectService.findById(idNewProject);
        newPu.setUserProject(projects);
        newPu.setIsPM((long) 0);
        ProjectUser addNewPu = projectUserService.addUserProject(newPu);
        if (addNewPu != null) {
            projectUserService.deletePro(pu);
        }
        return new ResponseEntity<ProjectUser>(
                addNewPu, HttpStatus.CREATED
        );
    }

    //test
    @GetMapping("/a")
    public List<UserProjectDTO> get() {
        return projectService.getUserProject((long) 1);
    }
}
