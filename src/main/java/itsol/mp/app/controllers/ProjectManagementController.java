package itsol.mp.app.controllers;

import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.repositories.UserRepository;
import itsol.mp.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectManagementController {

    @Autowired
    private ProjectService projectService;

//    @Autowired
//    private UserRepository userRepository;

    @GetMapping("/list/{username}")
    public List<ProjectDTO> getProject(@PathVariable String username){
        if(projectService.getProjectByPM(username).size() > 0 ) {
            return projectService.getProjectByPM(username);
        }else {
            return projectService.getListProject();
        }

    }
}
