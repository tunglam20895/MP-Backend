package itsol.mp.app.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "DIVISION_ID",referencedColumnName = "ID")
    Divisons divisons;

    @OneToOne(mappedBy = "users")
    Divisons divison;

    @Column(name = "ROLE")
    String role;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "PHONE")
    Long phone;

    @Column(name = "AVARTAR")
    String avartar;

    @Column(name = "FIRST_NAME")
    String firstName;

    @Column(name = "MIDDLE_NAME")
    String middleName;

    @Column(name = "LAST_NAME")
    String lastName;

    @Column (name = "TYPE")
    Long type;

    @Column(name = "PERSONAL_ID")
    Long personalId;

    @Column(name = "HOME_TOWN")
    String homeTown;

    @Column(name = "EDUCATION")
    String education;

    @Column(name = "SCHOOL")
    String school;

    @Column(name = "MAJOR")
    String major;

    @Column(name = "status")
    Long status;

    @Column(name = "DAY_OFF_LAST_YEAR")
    Long dayOffLastYear;

    @Column(name = "DATE_CREATED")
    Date dateCreated;

}
