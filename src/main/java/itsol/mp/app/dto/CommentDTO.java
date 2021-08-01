package itsol.mp.app.dto;

import lombok.Data;

@Data
public class CommentDTO {
    long issueId;
    String username;
    String content;
}
