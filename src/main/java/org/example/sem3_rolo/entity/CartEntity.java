package org.example.sem3_rolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "cart")
@Getter
@Setter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_gen")
    @SequenceGenerator(name = "cart_seq_gen", sequenceName = "cart_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "bag_id", nullable = false)
    private BagEntity bag;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}