package itsol.mp.app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddIssueDTO {
    String type;
    String title;
    String priority;
    String target;
    Long project;
    String descriptions;
}
