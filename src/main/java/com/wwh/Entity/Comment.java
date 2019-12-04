package com.wwh.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
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
    private Date createTime;

    @ManyToOne
    private Blog blog;

    //自包含，代表包含多个回复的子类对象
    @OneToMany(mappedBy = "parentComment", fetch = FetchType.EAGER)
    private List<Comment> replyComments;

    @ManyToOne
    private Comment parentComment;

}
