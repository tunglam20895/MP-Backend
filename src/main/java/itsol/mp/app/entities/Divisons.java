package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "DIVISIONS")
public class Divisons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "MANAGER_ID",referencedColumnName = "ID")
    Users manager;

    @JsonIgnore
    @OneToMany(mappedBy = "divisions",fetch = FetchType.LAZY)
    Collection<Users> users;

    @JsonIgnore
    @OneToMany(mappedBy = "divisonsProject",fetch = FetchType.LAZY)
    Collection<DivisonProject> divisonProjects;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;



}
