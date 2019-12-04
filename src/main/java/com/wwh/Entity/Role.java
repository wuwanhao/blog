package com.wwh.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 255,nullable = false)
    private String name;

    @NotNull
    @Column(length = 255,nullable = false )
    private String label;
}
