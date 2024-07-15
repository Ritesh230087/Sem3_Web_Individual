package org.example.sem3_rolo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"User\"") // Quoting "User" table name
@Getter
@Setter
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_setup_seq_gen")
    @SequenceGenerator(name = "parameter_setup_seq_gen", sequenceName = "parameters_setup_seq", allocationSize = 1)
    @Id
    private Integer id;

    @Column(name="Username")
    private String username;

    @Column(name="FullName")
    private String full_name;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="Address")
    private String address;
}
