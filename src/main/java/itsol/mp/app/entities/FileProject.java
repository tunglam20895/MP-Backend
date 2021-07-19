package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "FILE_PROJECT")
public class FileProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FILE_ID",referencedColumnName = "ID")
    Files fileProject;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROJECTS_ID",referencedColumnName = "ID")
    Projects projectFile;

    @Column(name="CATEGORY")
    String category;

}
