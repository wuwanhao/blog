package com.wwh.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


//博客实体
@Data
@Entity
@Table(name = "t_blog")
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
    @Column(nullable = false)
    private String firstPicture;

    //标记
    @Column(nullable = false)
    private String flag;

    //浏览次数
    @Column(nullable = false)
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //更新时间
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}
