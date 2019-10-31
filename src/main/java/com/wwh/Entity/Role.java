package com.wwh.Entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(length = 255,nullable = false)
    private String name;

    @NotNull
    @Column(length = 255,nullable = false )
    private String label;
}
