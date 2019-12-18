package com.wwh.Controller;

import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import com.wwh.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    TypeService typeService;

    //列出分类列表
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    //分类新增页面
    @GetMapping("/types/input")
    public String input(){
        return "admin/types_input";
    }

    //分类新增页面编辑完成后添加
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes redirectAttributes){
        Type t = typeService.saveType(type);
        if (t == null) {
            redirectAttributes.addFlashAttribute("message","操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }


    //分类修改跳转
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types_input";

    }

    //分类修改完成后提交
    @PostMapping("/types/{id}")
    public String editPost(Type type, @PathVariable Long id,RedirectAttributes redirectAttributes){
        Type t = typeService.updateType(id, type);
        if (t == null) {
            redirectAttributes.addFlashAttribute("message","操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }

    //分类删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id) throws Exception {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }






}
