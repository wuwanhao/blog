package com.wwh.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    //分类名
    @Column(nullable = false)
    private String name;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private List<Blog> blogs;
}
