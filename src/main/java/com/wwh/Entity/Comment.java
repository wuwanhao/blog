package com.wwh.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    //评论昵称
    private String nickName;

    //评论邮箱
    private String email;

    //评论内容
    private String content;

    //评论头像
    private String avatar;

    //评论创建时间,对应到数据库中的时间戳
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

}
