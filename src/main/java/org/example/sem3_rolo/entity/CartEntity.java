package org.example.sem3_rolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Cart")
@Getter
@Setter
public class CartEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_setup_seq_gen")

    @SequenceGenerator(name = "parameter_setup_seq_gen", sequenceName = "parameters_setup_seq", allocationSize = 1)

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "foreign_key_user_details_id"))
    private UserEntity User;
}
