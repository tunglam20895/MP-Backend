package itsol.mp.app.services;

import itsol.mp.app.dto.ProjectDTO;
import itsol.mp.app.dto.TransferDTO;
import itsol.mp.app.dto.UserProjectDTO;
import itsol.mp.app.entities.Projects;
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

    public List<UserProjectDTO> getUserProject(Long id){
        return projectRepository.getUserProject(id);
    }

    public Projects findById(long id){
        return projectRepository.findById(id);
    }

    public List<Projects> getTransferProject(Long id){
        return projectRepository.findAllByIdNot(id);
    }

    public List<Projects> getTransferProject(long id){
        return projectRepository.findAllByIdNot(id);
    }

}
