package com.wwh.Controller;

import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import com.wwh.Service.TypeService;
import com.wwh.utils.Result;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/type")
public class TypeAdminController {

    @Autowired
    TypeService typeService;

    @Autowired
    TypeRepository typeRepository;

    //后台分类列表(thymeleaf渲染)
    @GetMapping("/list")
    public String list(Model model) throws Exception {
        //查询所有分类
        List<Type> type = typeRepository.findAll();
        // Collections.sort(type);
        model.addAttribute("typeList",type);
        return "admin/types";
    }




    //分类添加
    @PostMapping("/add")
    public Type addType(@RequestBody Type type) throws Exception {
        return typeService.addType(type);
    }

    //分类名称修改
    @PostMapping("/edit/{id}/{name}")
    public Result editType(@PathVariable Long id, @PathVariable String name) throws Exception {
        typeService.updateType(id, name);
        return new Result(200,"分类更新成功");
    }

    //分类删除
    @DeleteMapping("/{id}/delete")
    public Result deleteType(@PathVariable Long id) throws Exception {
        typeService.deleteType(id);
        return new Result(200,"分类删除成功");
    }



}
