package org.example.sem3_rolo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Bag")
@Getter
@Setter
public class BagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_setup_seq_gen")
    @SequenceGenerator(name = "parameter_setup_seq_gen", sequenceName = "parameters_setup_seq", allocationSize = 1)
    private Integer bagId;

    @Column(name = "Bag_name")
    private String bagName;

    @Column(name = "Description")
    private String bagDescription;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Quantity")
    private Double quantity;

    @Column(name = "bagimage")
    private String bagImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "Category_Id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "foreign_key_category_details_id"))
    private CategoryEntity category;
}

