package com.wwh.Controller;

import com.wwh.Exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index() {
        return "admin/input";
    }
}
