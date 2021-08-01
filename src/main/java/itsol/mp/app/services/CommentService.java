package itsol.mp.app.services;

import itsol.mp.app.entities.Comments;
import itsol.mp.app.entities.Issues;
import itsol.mp.app.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comments> getCmtByIssue(Issues issues){
        return commentRepository.findByIssueComment(issues);
    }

    public Comments addCmt(Comments comments){
        return commentRepository.saveAndFlush(comments);
    }

    public void deleteCmtByIssue(Issues issues){
        commentRepository.deleteAllByIssueComment(issues);
    }
}
