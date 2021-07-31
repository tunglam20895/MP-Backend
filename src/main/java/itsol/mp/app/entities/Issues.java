package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "ISSUES")
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_CREATED",referencedColumnName = "ID")
    Users userCreated;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "ASSIGNEE",referencedColumnName = "ID")
//    Users userAssignee;

    @ManyToOne
    @JoinColumn(name = "PROJECTSID",referencedColumnName = "ID")
    Projects issueProject;

    @JsonIgnore
    @OneToMany(mappedBy = "issueFile",fetch = FetchType.LAZY)
    Collection<FileIssue> fileIssues;

    @Column(name = "STATUS")
    String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PARENT_ISSUE",referencedColumnName = "ID")
    Issues issues;

    @JsonIgnore
    @OneToMany(mappedBy = "issues",fetch = FetchType.LAZY)
    Collection<Issues> issueChild;

    @JsonIgnore
    @OneToMany(mappedBy = "issuesTimeLog",fetch = FetchType.LAZY)
    Collection<TimeLog> timeLogsIssue;

    @JsonIgnore
    @OneToMany(mappedBy = "issueComment",fetch = FetchType.LAZY)
    Collection<Comments> comments;

    @Column(name = "TYPE")
    String type;

    @Column(name = "TITLE")
    String title;

    @Column(name = "DESCRIPTION")
    String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_START")
    Date dateStrarted;

    @Column(name = "SOULUTION")
    String soulution;

    @Column(name = "PROGRESS")
    Long progress;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_END")
    Date dateEnd;

    @Column(name = "PRIORITY")
    String priority;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_UPDATE")
    Date dateUpdate;

    @Column(name = "TARGET")
    String target;
}
