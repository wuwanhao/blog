package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Service.BlogService;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    BlogService blogService;

    //列出博客
    @GetMapping("/list")
    public List<BlogNameVO> getBlogNameList(@Valid @PathVariable BlogQO blogQO) throws Exception {
        return blogService.getBlogNameList(blogQO);
    }

    //查看博客详细信息
    @GetMapping("/{id}/detail")
    public BlogDetailVO getDetail(@PathVariable Long id) throws Exception {
        return blogService.getDetail(id);
    }



}
