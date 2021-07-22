package itsol.mp.app.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProjectDTO {
    Long memberId;
    Long id;
    Long projectId;
    String username;
//    String avartar;
    String firstName;
    String lastName;
    String email;

//    @JsonCreator
//    public UserProjectDTO(@JsonProperty("memberId") Long memberId, @JsonProperty("id") Long id,
//                          @JsonProperty("projectId") Long projectId,@JsonProperty("username") String username,
//                          @JsonProperty("avartar")String avartar, @JsonProperty("firstName") String firstName,
//                          @JsonProperty("lastName") String lastName,@JsonProperty("email") String email) {
//        this.memberId = memberId;
//        this.id = id;
//        this.projectId = projectId;
//        this.username = username;
//        this.avartar = avartar;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
}
