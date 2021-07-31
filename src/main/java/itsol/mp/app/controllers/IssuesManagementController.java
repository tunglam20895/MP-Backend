package itsol.mp.app.controllers;

import itsol.mp.app.dto.AddIssueDTO;
import itsol.mp.app.entities.Issues;
import itsol.mp.app.services.IssueService;
import itsol.mp.app.services.ProjectService;
import itsol.mp.app.services.UserService;
import itsol.mp.app.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssuesManagementController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    // Lấy danh sách issue
    @GetMapping("/issues/{username}")
    public ResponseEntity<List<?>> getAllIssues(@PathVariable String username) {
        if (issueService.getIssueByMember(username).size() > 0) {
            return new ResponseEntity<>(
                    issueService.getIssueByMember(username), HttpStatus.OK
            );
        } else if (issueService.getIssueByPM(username).size() > 0) {
            return new ResponseEntity<>(
                    issueService.getIssueByPM(username), HttpStatus.OK
            );
        } else if(userService.findByRoleAdmin(username) != null){
            System.out.println(userService.findByRoleAdmin(username).getRole() + username);
            return new ResponseEntity<>(
                    issueService.getIssueByAdmin(), HttpStatus.OK
            );
        }else{
            return null;
        }
    }

    //Lấy danh sách project theo username(chức năng tạo mới issue)
    @GetMapping("/get-project/{username}")
    public ResponseEntity<List<?>> getProjectByUser(@PathVariable String username){
        if(projectService.getProjectByUsername(username).size() > 0){
            return new ResponseEntity<>(
                    projectService.getProjectByUsername(username),HttpStatus.OK
            );
        }
        return new ResponseEntity(
                new CustomErrorType("Lỗi " + username + " không có trong project nào"),HttpStatus.BAD_REQUEST
        );
    }

    Date date = new Date();
    //Tạo issue
    @PostMapping("/create-issue/{username}")
    public ResponseEntity<?> createIssue(@RequestBody AddIssueDTO newIssue,@PathVariable String username){
        System.out.println(newIssue.getProject());
        if(projectService.findById(newIssue.getProject()) == null){
            return new ResponseEntity(
                    new CustomErrorType("Lỗi project không hợp lệ"),HttpStatus.BAD_REQUEST
            );
        }
        Issues issues = new Issues();
        issues.setStatus("new");
        issues.setType(newIssue.getType());
        issues.setTitle(newIssue.getTitle());
        issues.setDescription(newIssue.getDescriptions());
        issues.setDateStrarted(date);
        issues.setPriority(newIssue.getPriority());
        issues.setTarget(newIssue.getTarget());
        issues.setDateUpdate(date);
        issues.setIssueProject(projectService.findById(newIssue.getProject()));
        issues.setUserCreated(userService.findUserByUsername(username));
        return new ResponseEntity(
                issueService.addIssue(issues),HttpStatus.CREATED
        );
    }

    //Xóa issue
    @DeleteMapping("/delete-issue/{id}")
    public void deleteIssue(@PathVariable long id){
        if(issueService.findById(id) == null){
            return;
        }
        issueService.deleteIssueById(id);
    }
}
