package itsol.mp.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "DIVISIONS")
public class Divisons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "MANAGER_ID",referencedColumnName = "ID")
    private Users users;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;



}
