package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "DIVISIONS")
public class Divisons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
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
