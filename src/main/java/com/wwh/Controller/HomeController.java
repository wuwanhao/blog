package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.Repository.BlogRepository;
import com.wwh.Service.BlogService;
import com.wwh.Service.UserService;
import com.wwh.VO.BlogDetailVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogService blogService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/types")
    public String type() {
        return "types";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/auth/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    //登陆成功后处理
    @RequestMapping(value = "/admin/index")
    public String successfulPage() {
        return "admin/index";
    }

//    @RequestMapping(value = "/admin/blogs")
//    public String adminBlog() {
//        return "admin/blogs";
//    }
//
//    @RequestMapping(value = "/admin/blogs_input")
//    public String adminBlogInput() {
//        return "admin/blogs_input";
//    }
//
//    @RequestMapping(value = "/admin/types")
//    public String adminType() {
//        return "admin/types";
//    }
//
//    @GetMapping(value = "/admin/types_input")
//    public String adminTypeInput() {
//        return "admin/types_input";
//    }


    @GetMapping("/search")
    public String search(String keyWord, Model model) throws Exception {
        List<Blog> blogs = blogRepository.searchBlog(keyWord);
        model.addAttribute("blogSearchNum", blogs.size());
        model.addAttribute("blogSearchList",blogs);

        return "search_result";
    }

    //查看博客详细信息
    @ApiOperation("查看博客详细信息")
    @GetMapping("/{id}/detail")
    public String getDetail(@PathVariable Long id, Model model) throws Exception {
        BlogDetailVO blogDetailVO = blogService.getDetail(id);
        System.out.println("999" + blogDetailVO);
        model.addAttribute("title", blogDetailVO.getTitle());
        model.addAttribute("content", blogDetailVO.getContent());
        model.addAttribute("firstPicture", blogDetailVO.getFirstPicture());
        model.addAttribute("flag", blogDetailVO.getFlag());
        model.addAttribute("views", blogDetailVO.getViews());
        model.addAttribute("createTime", blogDetailVO.getCreateTime());
        model.addAttribute("type", blogDetailVO.getType().getName());
        model.addAttribute("username", blogDetailVO.getUser().getUsername());
        System.out.println("666" + blogDetailVO.getUser().getUsername());
        model.addAttribute("userAvatar",blogDetailVO.getUser().getAvatar());

        return "blog";


    }



}
