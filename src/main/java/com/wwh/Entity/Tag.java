package com.wwh.Entity;


import lombok.Data;


import javax.persistence.*;


@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标签名
    @Column(nullable = false)
    private String name;

}
