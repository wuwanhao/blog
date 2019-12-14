package com.wwh.Entity;


import lombok.Data;


import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //分类名
    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<Blog> blogs;

}
