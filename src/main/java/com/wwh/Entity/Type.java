package com.wwh.Entity;


import lombok.Data;


import javax.persistence.*;


@Data
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //分类名
    @Column(nullable = false)
    private String name;

}
