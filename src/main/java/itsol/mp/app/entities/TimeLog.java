package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="TIME_LOG")
public class TimeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ISSUE_ID",referencedColumnName = "ID")
    Issues issuesTimeLog;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USERS_ID",referencedColumnName = "ID")
    Users userTimeLog;

    @Column(name= "DATE_LOG")
    Date dateLog;

    @Column(name= "TIME_LOG")
    Long timeLog;
}
