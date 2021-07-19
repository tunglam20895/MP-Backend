package itsol.mp.app.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "LEAVE_REQUESTS")
public class LeaveRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_REQUESTED",referencedColumnName = "ID")
    Users usersRequest;

    @ManyToOne
    @JoinColumn(name = "USER_APPROVED",referencedColumnName = "ID")
    Users usersApproved;

    @ManyToOne
    @JoinColumn(name = "TYPE",referencedColumnName = "ID")
    RequestType requestType;

    @Column(name = "DATE_CREATED")
    Date dateCreated;

    @Column(name = "DATE_APPROVED")
    Date dateApproved;

    @Column(name = "STATUS")
    Long status;

    @Column(name = "DATE_REQUESTED")
    Date dateRequested;

    @Column(name = "DURATION")
    Long duration;

}
