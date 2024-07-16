package org.example.sem3_rolo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartPojo {
    private Integer id;
    private Integer userId;  // Assuming userId is used to reference UserEntity
}
