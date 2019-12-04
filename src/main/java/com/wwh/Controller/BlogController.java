package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Service.BlogService;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogNameItemVO;
import com.wwh.VO.BlogNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    public BlogNameItemVO getBlogNameList(@Valid BlogQO blogQO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        return blogService.getBlogNameList(blogQO);
    }

    //查看博客详细信息
    @GetMapping("/{id}/detail")
    public BlogDetailVO getDetail(@PathVariable Long id) throws Exception {
        return blogService.getDetail(id);
    }



}
