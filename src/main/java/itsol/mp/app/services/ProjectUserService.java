package itsol.mp.app.services;

import itsol.mp.app.entities.ProjectUser;
import itsol.mp.app.repositories.ProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserService {
    @Autowired
    private ProjectUserRepository projectUserRepository;

    public void deletePro(ProjectUser projectUser){
        projectUserRepository.delete(projectUser);
    }

    public ProjectUser addUserProject(ProjectUser projectUser){
        return projectUserRepository.save(projectUser);
    }

    public ProjectUser findById(long id){
       return projectUserRepository.findProjectUserById(id);
    }
}
