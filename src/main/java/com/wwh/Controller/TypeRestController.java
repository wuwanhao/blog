package com.wwh.Controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import com.wwh.Service.TypeService;
import com.wwh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.TypeElement;

@RestController
@RequestMapping("/type")
public class TypeRestController {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    TypeService typeService;

    //新增分类
    @PostMapping("/add")
    public Result<?> addType(Type type) throws Exception {
        Type newType = new Type();
        newType.setName(type.getName());
        typeRepository.save(newType);
        return new Result<>(200,"新增分类成功");
    }

    //删除分类
    @RequestMapping("/delete")
    public Result<?> deleteType(Long id) throws Exception {
        typeRepository.deleteById(id);
        return new Result<>(200,"分类删除成功");
    }

    //修改分类名称
    @RequestMapping("/update")
    public Result<?> updateType(Long id, String name) throws Exception {
        typeService.updateType(id, name);
        return new Result<>(200,"修改分类名称成功");
    }

    //查询分类
    @GetMapping("/info")
    public Type getType(Long id) throws Exception {
        return typeRepository.getOne(id);
    }

    //




}
