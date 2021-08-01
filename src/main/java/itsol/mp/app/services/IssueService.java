package itsol.mp.app.services;

import itsol.mp.app.dto.IssueDTO;
import itsol.mp.app.entities.Issues;
import itsol.mp.app.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public Issues addIssue(Issues issues){
        return issueRepository.saveAndFlush(issues);
    }

    public  Issues updateIssues( Issues issues){
        return issueRepository.save(issues);
    }

    public List<IssueDTO> getIssueByMember(String username){
        return issueRepository.getIssuesByMember(username);
    }

    public List<IssueDTO> getIssueByPM(String username){
        return issueRepository.getIssuesByPM(username);
    }

    public List<Issues> getIssueByAdmin(){
        return issueRepository.getIssuesByAdmin();
    }

    public void deleteIssueById(long id){
         issueRepository.deleteById(id);
    }

    public Issues findById(long id){
        return issueRepository.findById(id);
    }
}
