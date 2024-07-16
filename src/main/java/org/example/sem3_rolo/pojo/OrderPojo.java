package org.example.sem3_rolo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sem3_rolo.entity.BagEntity;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPojo {
    private Integer userId;
    private List<BagEntity> bags;
    private Date orderDate;
    private Double totalAmount;
}
