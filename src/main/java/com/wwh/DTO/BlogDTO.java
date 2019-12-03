package com.wwh.DTO;

import com.wwh.Entity.Tag;
import com.wwh.Entity.Type;
import com.wwh.Entity.User;
import lombok.Data;
import java.util.List;

@Data
public class BlogDTO {


    //标题
    private String title;

    //内容
    private String content;

    //首图
    private String firstPicture;

    //标记
    private String flag;

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


    private Type type;

    private User user;


    private List<Tag> tags;


}
