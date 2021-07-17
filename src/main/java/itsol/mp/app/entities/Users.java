package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID")
    Divisons divisions;

    @JsonIgnore
    @OneToOne(mappedBy = "manager", fetch = FetchType.LAZY)
    Divisons divisons;

    @JsonIgnore
    @OneToMany(mappedBy = "reportUsers", fetch = FetchType.LAZY)
    Collection<Reports> reports;

    @JsonIgnore
    @OneToMany(mappedBy = "projectUser", fetch = FetchType.LAZY)
    Collection<ProjectUser> projectUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "usersRequest", fetch = FetchType.LAZY)
    Collection<LeaveRequests> reqLeaveRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "usersApproved", fetch = FetchType.LAZY)
    Collection<LeaveRequests> appLeaveRequests;

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

    @Column(name = "TYPE")
    Long type;

    @Column(name = "PERSONAL_ID")
    Long personalId;

    @Column(name = "HOME_TOWN_")
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

    @Column(name = "BIRTH_DAY")
    String birthDay;

}
