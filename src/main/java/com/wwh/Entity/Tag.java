package com.wwh.Entity;


import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    //标签名
    @Column(nullable = false)
    private String name;

}
