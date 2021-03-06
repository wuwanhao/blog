package com.wwh.Controller;

import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import com.wwh.Service.TypeService;
import com.wwh.VO.TypeSearchResultVO;
import com.wwh.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "分类后台管理")
@RequestMapping("/admin/type")
public class TypeAdminController {

    @Autowired
    TypeService typeService;

    @Autowired
    TypeRepository typeRepository;

//    //后台分类列表(thymeleaf渲染)
//    @GetMapping("/list")
//    public String list(Model model) throws Exception {
//        //查询所有分类
//        List<Type> type = typeRepository.findAll();
//        // Collections.sort(type);
//        model.addAttribute("typeList",type);
//        return "admin/types";
//    }




    //分类添加
    @ApiOperation("分类添加")
    @PostMapping("/add")
    public Result addType(String name) throws Exception {
        typeService.addType(name);
        return new Result(200,"分类添加成功","");
    }

    //分类名称修改
    @ApiOperation("分类修改")
    @PostMapping("/{id}/edit")
    public Result editType(@PathVariable Long id, String name) throws Exception {
        typeService.updateType(id, name);
        return new Result(200,"分类更新成功");
    }

    //分类删除
    @ApiOperation("分类删除")
    @DeleteMapping("/{id}/delete")
    public Result deleteType(@PathVariable Long id) throws Exception {
        typeService.deleteType(id);
        return new Result(200,"分类删除成功");
    }

    //分类搜索
    @ApiOperation("分类搜索(不可用)")
    @GetMapping("/search")
    public List<TypeSearchResultVO> search(String keyWords) throws Exception {
        return typeService.search(keyWords);
    }



}
