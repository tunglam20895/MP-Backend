package itsol.mp.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "DIVISIOB_PROJECT")
public class DivisonProject {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID" ,referencedColumnName = "ID")
    Projects projectsDivison;

    @ManyToOne
    @JoinColumn(name = "DIVISIONID", referencedColumnName = "ID")
    Divisons divisonsProject;

}
