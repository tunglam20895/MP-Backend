package itsol.mp.app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddProjectDTO {
    String name;
    Date dateEnd;
    String username;
    String descriptions;
}
