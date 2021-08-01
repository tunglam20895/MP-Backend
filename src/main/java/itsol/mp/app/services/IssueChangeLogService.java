package itsol.mp.app.services;

import itsol.mp.app.entities.IssueChangeLog;
import itsol.mp.app.entities.Issues;
import itsol.mp.app.repositories.IssueChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueChangeLogService {

    @Autowired
    private IssueChangeLogRepository issueChangeLogRepository;

    public IssueChangeLog addNewChange(IssueChangeLog issueChangeLog){
        return issueChangeLogRepository.saveAndFlush(issueChangeLog);
    }

    public List<IssueChangeLog> finallByIssue(Issues issues){
        return issueChangeLogRepository.findAllByIssueChangeLog(issues);
    }

    public void deleteLogByIssue(Issues issues){
        issueChangeLogRepository.deleteAllByIssueChangeLog(issues);
    }
}
