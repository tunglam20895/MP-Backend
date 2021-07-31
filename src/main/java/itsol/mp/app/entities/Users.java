package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itsol.mp.app.entities.enums.UserStatus;
import itsol.mp.app.entities.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
//    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "MP", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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

    @JsonIgnore
    @OneToOne(mappedBy = "uploader",fetch = FetchType.LAZY)
    Files files;

    @JsonIgnore
    @OneToMany(mappedBy = "userCreated",fetch = FetchType.LAZY)
    Collection<Issues> issueUserCreated;

//    @JsonIgnore
//    @OneToMany(mappedBy = "userAssignee" ,fetch = FetchType.LAZY)
//    Collection<Issues> issueUserAssignee;

    @JsonIgnore
    @OneToMany(mappedBy = "userTimeLog",fetch = FetchType.LAZY)
    Collection<TimeLog> timeLogUser;

    @JsonIgnore
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    List<Comments> comments;

    @Column(name = "ROLE",nullable = true)
    String role;

    @Column(name = "USERNAME",nullable = true)
    String username;

    @Column(name = "PASSWORD",nullable = true)
    String password;

    @Column(name = "EMAIL",nullable = true)
    String email;

    @Column(name = "PHONE",nullable = true)
    Long phone;

    @Column(name = "AVARTAR",nullable = true)
    String avartar;

    @Column(name = "FIRST_NAME",nullable = true)
    String firstName;

    @Column(name = "MIDDLE_NAME",nullable = true)
    String middleName;

    @Column(name = "LAST_NAME",nullable = true)
    String lastName;

    @Column(name = "TYPE",nullable = true)
    Long type;

    @Transient
    private UserType userType;

    @Column(name = "status")
    Long status;

    @Transient
    private UserStatus userStatus;

    @PostLoad
    void fillTransientStatus() {
        if (status >= 0  && status < 3) {
            this.userStatus = UserStatus.of(status);
        }
        if (type >= 0  && type < 3) {
            this.userType = UserType.of(type);
        }
    }

    @PrePersist
    void fillPersistentStatus() {
        if (userStatus != null) {
            this.status = (long) userStatus.getStatus(status);
        }
        if (userType != null) {
            this.type = (long) userType.getType(type);
        }
    }


    @Column(name = "PERSONAL_ID",nullable = true)
    Long personalId;

    @Column(name = "HOME_TOWN_",nullable = true)
    String homeTown;

    @Column(name = "EDUCATION",nullable = true)
    String education;

    @Column(name = "SCHOOL",nullable = true)
    String school;

    @Column(name = "MAJOR",nullable = true)
    String major;

    @Column(name = "DAY_OFF_LAST_YEAR",nullable = true)
    Long dayOffLastYear;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    Date dateCreated;

    @Column(name="GENDER")
    String gender;

}
