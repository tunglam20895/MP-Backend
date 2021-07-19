package itsol.mp.app.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "FILES")
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "NAME")
    String name;

    @Column(name = "PATH")
    String path;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "UPLOADER",referencedColumnName = "ID")
    Users uploader;

    @JsonIgnore
    @OneToMany(mappedBy = "fileProject",fetch = FetchType.LAZY)
    Collection<FileProject> fileProject;

    @JsonIgnore
    @OneToMany(mappedBy = "fileIssue",fetch = FetchType.LAZY)
    Collection<FileIssue> fileIssues;

}
