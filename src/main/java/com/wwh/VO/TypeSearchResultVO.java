package com.wwh.VO;

import com.wwh.Entity.Blog;
import lombok.Data;

import java.util.List;

@Data
public class TypeSearchResultVO {

    //搜索结果数量
    private int number;

    List<BlogNameVO> blogNameVOS;

}
