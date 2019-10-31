package com.wwh.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String email;

    private String avatar;

    //用户类型
    private int type;

    //时间戳
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //时间戳
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
