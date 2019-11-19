package com.wwh.Controller;

import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import com.wwh.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @Autowired
    TypeRepository typeRepository;

    //后台分类列表
    @GetMapping("/list")
    public String list(Model model) throws Exception {
        //查询所有分类
        List<Type> type = typeRepository.findAll();
        // Collections.sort(type);
        model.addAttribute("typeList",type);
        return "admin/types";
    }

}
