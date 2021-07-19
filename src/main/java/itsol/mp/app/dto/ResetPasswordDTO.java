package itsol.mp.app.dto;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String username;
    private String email;
}
