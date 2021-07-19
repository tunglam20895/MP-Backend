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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_MODIFIED", referencedColumnName = "ID")
    Users usersModified;

    @Column(name = "TITLE")
    String title;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "CHANGE_DATE")
    Date changeDate;

    @Column(name = "START_DATE")
    Date startDate;

    @Column(name = "DUE_DATE")
    Date dueDate;

    @Column(name = "TARGET")
    Long target;

    @Column(name = "PRIORITY")
    Long priority;

    @Column(name = "PROCESS")
    Long process;

    @Column(name = "SOLUTION")
    String solution;

    @Column(name = "TIME_SPEND")
    Long timeSpend;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "COMMENTS")
    String comments;
}
