package com.wwh.Controller;

import com.wwh.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

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

    @RequestMapping(value = "/admin/blogs")
    public String adminBlog() {
        return "admin/blogs";
    }

    @RequestMapping(value = "/admin/blogs_input")
    public String adminBlogInput() {
        return "admin/blogs_input";
    }

    @RequestMapping(value = "/admin/types")
    public String adminType() {
        return "admin/types";
    }

    @GetMapping(value = "/admin/types_input")
    public String adminTypeInput() {
        return "admin/types_input";
    }


}
