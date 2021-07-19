package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "ISSUES")
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "USER_CREATED",referencedColumnName = "ID")
    Users userCreated;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ASSIGNEE",referencedColumnName = "ID")
    Users userAssignee;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROJECTSID",referencedColumnName = "ID")
    Projects issueProject;

    @JsonIgnore
    @OneToMany(mappedBy = "issueFile",fetch = FetchType.LAZY)
    Collection<FileIssue> fileIssues;

    @Column(name = "STATUS")
    Long status;

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

}
