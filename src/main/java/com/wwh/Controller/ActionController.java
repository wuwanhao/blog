package com.wwh.Controller;

import com.wwh.Service.BlogService;
import com.wwh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {


    @Autowired
    BlogService blogService;


    //博客点赞
    @RequestMapping("/blog/{id}/star")
    public Integer starBlog(@PathVariable Long id){
        return blogService.starBlog(id);
    }
}
