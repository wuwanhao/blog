package com.wwh.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
