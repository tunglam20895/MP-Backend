package itsol.mp.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)

@Entity
@Table(name = "PROJECT_USER")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "PROJECTID",referencedColumnName = "ID")
    Projects userProject;

    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    Users projectUser;

    @Column(name = "ISPM")
    Long isPM;
}
