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
@Table(name = "COMMENTS")

public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "OWNER",referencedColumnName = "ID")
    Users owner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ISSUESS_ID",referencedColumnName = "ID")
    Issues issueComment;

    @Column(name = "CONTENT")
    String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_COMMENT")
    Date dateComment;
}
