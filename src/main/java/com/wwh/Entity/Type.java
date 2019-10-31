package com.wwh.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    //分类名
    @Column(nullable = false)
    private String name;
}
