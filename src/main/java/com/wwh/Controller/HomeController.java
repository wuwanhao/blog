package com.wwh.Controller;

import com.wwh.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin/login")
    public String loginPage(){
        return "admin/login";
    }


    @RequestMapping("/admin/index")
    public String successfulPage() {
        return "admin/index";
    }


}
