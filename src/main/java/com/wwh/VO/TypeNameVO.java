package com.wwh.VO;

import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import lombok.Data;

import java.util.List;

@Data
public class TypeNameVO {

    private Type type;

    private List<Blog> blogs;

    private int numberOfType;

}
