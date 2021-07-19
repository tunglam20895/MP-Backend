package itsol.mp.app.services;

import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getListProject(){
        return projectRepository.getallProject();
    }

    public List<ProjectDTO> getProjectByPM(String username){
        return projectRepository.getProjectByPM(username);
    }
}
