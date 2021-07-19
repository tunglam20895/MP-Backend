package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "FILE_ISSUE")
public class FileIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FILEID",referencedColumnName = "ID")
    Files fileIssue;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ISSUEID",referencedColumnName = "ID")
    Issues issueFile;
}
