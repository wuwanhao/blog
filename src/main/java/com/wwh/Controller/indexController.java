package com.wwh.Controller;

import com.wwh.Exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name) {
//        int i = 9/0;
//        String blog = "eee";
//        if (blog == null) {
//            throw new NotFoundException("博客不存在");
//        }
        System.out.println("------------index------------");
        return "index";
    }
}
