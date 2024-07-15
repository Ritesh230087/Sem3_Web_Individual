package org.example.sem3_rolo.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest {
    private String email;
    private String newPassword;
}
