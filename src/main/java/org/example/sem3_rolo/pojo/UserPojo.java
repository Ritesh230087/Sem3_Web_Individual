package org.example.sem3_rolo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
        private Integer id;
        private String username;
        private String full_name;
        private String email;
        private String password;
        private String address;
}
