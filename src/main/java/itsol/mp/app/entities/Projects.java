package itsol.mp.app.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PROJECTS")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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
