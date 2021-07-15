package itsol.mp.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor

@Entity
@Table(name = "REPORTS")
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "PROJECT_IDD",referencedColumnName = "ID")
    Projects projects;

    @ManyToOne
    @JoinColumn(name = "USERID",referencedColumnName = "ID")
    Users reportUsers;

    @Column(name = "ADVANTAGE")
    String advantage;

    @Column(name = "DISADVANTAGE")
    String disadvantage;

    @Column(name = "DIFICUTY")
    String dificuty;

    @Column(name = "PROPOSE")
    String propose;

    @Column(name = "DATE_CREATED")
    Date dateCreated;

    @Column(name = "DATE_READ")
    Date dateRead;
}
