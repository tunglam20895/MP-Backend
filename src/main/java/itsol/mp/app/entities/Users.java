package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import itsol.mp.app.entities.enums.UserStatus;
import itsol.mp.app.entities.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
//    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "MP", allocationSize = 1)
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

    @JsonIgnore
    @OneToOne(mappedBy = "uploader",fetch = FetchType.LAZY)
    Files files;

    @JsonIgnore
    @OneToOne(mappedBy = "userCreated",fetch = FetchType.LAZY)
    Issues issueUserCreated;

    @JsonIgnore
    @OneToMany(mappedBy = "userAssignee" ,fetch = FetchType.LAZY)
    Collection<Issues> issueUserAssignee;

    @JsonIgnore
    @OneToMany(mappedBy = "userTimeLog",fetch = FetchType.LAZY)
    Collection<TimeLog> timeLogUser;

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

    @Column(name = "DAY_OFF_LAST_YEAR")
    Long dayOffLastYear;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    Date dateCreated;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "BIRTH_DAY")
    String birthDay;

}
