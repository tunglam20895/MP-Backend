package itsol.mp.app.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor

public class ProjectDTO {
    Long id;
    String name;
    String description;
    Date dateStarted;
    Date dateEnd;
    String pmFirstName;
    String pmLastName;
}
