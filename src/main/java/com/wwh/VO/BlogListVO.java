package com.wwh.VO;

import com.wwh.Entity.Type;
import lombok.Data;

import java.util.Date;

@Data
public class BlogListVO {

    //标题
    private String title;

    //类型
    private Type type;

    //推荐
    private boolean recommend;

    //更新时间
    private Date updateTime;


}
