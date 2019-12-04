package com.wwh.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


//博客实体
@Data
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    @Column(nullable = false)
    private String title;

    //内容
    @Column(nullable = false)
    private String content;

    //首图
    private String firstPicture;

    //标记
    private String flag;

    //浏览次数
    private Integer views;


    //是否开启赞赏
    private boolean appreciation;
    //是否开启版权
    private boolean shareStatement;
    //是否开启评论
    private boolean commentabled;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;


    //创建时间
    @Column(nullable = false)
    private Date createTime;

    //更新时间
    private Date updateTime;

    //类型
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    //作者
    @ManyToOne
    @JoinColumn(name = "auther_id")
    private User auther;

    //标签
    @ManyToMany
    @JoinTable(
            name = "blog_tags",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    //评论
    @OneToMany
    private List<Comment> comments;

}
