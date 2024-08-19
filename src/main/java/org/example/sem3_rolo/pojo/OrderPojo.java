package org.example.sem3_rolo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPojo {
    private Integer id;
    private Integer userId;
    private Integer bagId;
    private Double totalAmount;
}
