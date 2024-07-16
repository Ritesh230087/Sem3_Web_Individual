package org.example.sem3_rolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "\"order\"") // Quoting "order" table name
@Getter
@Setter
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_setup_seq_gen")
    @SequenceGenerator(name = "parameter_setup_seq_gen", sequenceName = "parameters_setup_seq", allocationSize = 1)
    @Id
    private Integer id;

    @Column(name = "Order Date")
    private Date orderdate;

    @Column(name = "Total Amount")
    private Double totalamount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "foreign_key_user_details_id2")) // No need to quote here as it's just a column reference
    private UserEntity userEntity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bag_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "foreign_key_bag_details_id2")) // No need to quote here as it's just a column reference
//    private UserEntity bagEntity;


}
