package org.example.sem3_rolo.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BagPojo {
    private Integer bagId;
    private String bagName;
    private String bagDescription;
    private Double price;
    private Double quantity;
    private MultipartFile bagImage;
    private Integer categoryId;
}
