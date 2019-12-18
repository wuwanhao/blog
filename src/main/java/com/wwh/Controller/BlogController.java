package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.Service.BlogService;
import com.wwh.Service.TypeService;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogNameItemVO;
import com.wwh.VO.BlogNameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {



    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    //列出博客列表
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("types", typeService.list());
        model.addAttribute("page", blogService.listBlog(pageable));
        return "admin/blogs";
    }


    //博客新增
    @GetMapping("/blogs/input")
    public String input(Model model){
        //初始化分类列表
        model.addAttribute("types", typeService.list());
//        //初始化一个空的Blog对象
//        model.addAttribute("blog", new Blog());
        return "admin/blogs_input";
    }



    //博客发布
    @PostMapping("/blogs")
    public String post(Blog blog ,RedirectAttributes redirectAttributes) throws Exception {

        blog.setType(typeService.getType(blog.getType().getId()));
        Blog b;

        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null) {
            redirectAttributes.addFlashAttribute("message","操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    //博客修改页面
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        //初始化分类
        model.addAttribute("types", typeService.list());
        Blog blog = blogService.getBlog(id);
        System.out.println("取到的博客为：" + blog);
        model.addAttribute("blog", blog);
        return "admin/blogs_input";

    }


    //博客修改完成后保存
    @PostMapping("/blogs/{id}")
    public String editPost(Blog blog, @PathVariable Long id,RedirectAttributes redirectAttributes) throws Exception {
        Blog b = blogService.updateBlog(id, blog);
        if (b == null) {
            redirectAttributes.addFlashAttribute("message","操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }


    //博客删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id) throws Exception {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }




//    //定义局部渲染
//    @GetMapping("/blogs/search")
//    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
//                                Pageable pageable, Blog blog, Model model){
//        model.addAttribute("blog", blogService.listBlog(pageable));
//        return "admin/blogs :: blogList";
//    }








}
