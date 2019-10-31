package com.wwh.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    //分类名
    @Column(nullable = false)
    private String name;
}
