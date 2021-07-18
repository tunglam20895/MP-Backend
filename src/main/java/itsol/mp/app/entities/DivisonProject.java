package itsol.mp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "DIVISIOB_PROJECT")
public class DivisonProject {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID" ,referencedColumnName = "ID")
    Projects projectsDivison;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "DIVISIONID", referencedColumnName = "ID")
    Divisons divisonsProject;

}
