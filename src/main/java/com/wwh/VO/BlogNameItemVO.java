package com.wwh.VO;

import lombok.Data;

import java.util.List;

@Data
public class BlogNameItemVO {

    // 条目数量
    private long number;

    List<BlogNameVO> blogNameVOList;
}
