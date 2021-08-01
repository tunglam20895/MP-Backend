package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "ISSUE_CHANGE_LOG")
public class IssueChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ISSUES_ID", referencedColumnName = "ID")
    Issues issueChangeLog;


    @ManyToOne
    @JoinColumn(name = "USER_MODIFIED", referencedColumnName = "ID")
    Users usersModified;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATECHANGE")
    Date dateChange;
}
