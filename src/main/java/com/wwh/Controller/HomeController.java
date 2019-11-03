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

    @GetMapping("/auth/login")
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @RequestMapping(value = "/admin/index", method = RequestMethod.POST)
    public String successfulPage() {
        return "admin/index";
    }


}
