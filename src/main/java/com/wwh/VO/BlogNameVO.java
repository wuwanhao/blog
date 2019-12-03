package com.wwh.VO;

import com.wwh.Entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class BlogNameVO {

    //标题
    private String title;

    //内容
    private String content;

    //首图
    private String firstPicture;

    //标记
    private String flag;

    //浏览次数
    private Integer views;

    //创建时间
    private Date createTime;

    //作者
    private User user;
}
