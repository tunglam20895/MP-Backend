package itsol.mp.app.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProjectDTO {
    Long memberId;
    Long id;
    String avartar;
    String firstName;
    String lastName;
    String email;
}
