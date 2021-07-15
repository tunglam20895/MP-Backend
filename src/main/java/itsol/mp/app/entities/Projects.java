package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "PROJECTS")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "projectsDivison",fetch = FetchType.LAZY)
    Collection<DivisonProject> divisonProjects;

    @JsonIgnore
    @OneToMany(mappedBy = "userProject",fetch = FetchType.LAZY)
    Collection<ProjectUser> projectUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "projects",fetch = FetchType.LAZY)
    Collection<Reports> reports;

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
