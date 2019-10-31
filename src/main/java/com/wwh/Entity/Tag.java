package com.wwh.Entity;


import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @Fetch(FetchMode.SELECT)
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private List<Blog> blogs;
}
