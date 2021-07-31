package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "PROJECTS")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "projectsDivison", fetch = FetchType.LAZY)
    Collection<DivisonProject> divisonProjects;

    @JsonIgnore
    @OneToMany(mappedBy = "userProject", fetch = FetchType.LAZY)
    Collection<ProjectUser> projectUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    Collection<Reports> reports;

    @JsonIgnore
    @OneToMany(mappedBy = "projectFile", fetch = FetchType.LAZY)
    Collection<FileProject> projectFile;

    @JsonIgnore
    @OneToMany(mappedBy = "issueProject", fetch = FetchType.LAZY)
    Collection<Issues> projectIssue;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "DATE_STARTED")
    Date dateStarted;

    @Column(name = "DATE_END")
    Date dateEnd;

    @Column(name = "STATUS")
    Long status;
}
