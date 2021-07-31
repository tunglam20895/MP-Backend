package itsol.mp.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itsol.mp.app.entities.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IssueDTO {
    Long id;

    Projects issueProject;

    String status;

    String type;

    String title;

    String description;

    Date dateStrarted;

    String soulution;

    Long progress;

    Date dateEnd;

    String priority;

    String username;

    String role;

    Date dateUpdate;

    String target;

}
