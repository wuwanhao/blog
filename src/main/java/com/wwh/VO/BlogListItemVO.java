package com.wwh.VO;

import lombok.Data;

import java.util.List;

@Data
public class BlogListItemVO {

    //条目数量
    private long number;

    List<BlogListVO> blogListVOList;
}
