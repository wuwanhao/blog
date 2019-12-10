package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.Service.BlogService;
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

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {





    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    //按照更新时间倒序排列
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page", blogService.listBlog(pageable));
        return "admin/blogs";
    }


    @GetMapping("/blogs/input")
    public String input(){
        return "admin/blogs_input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes redirectAttributes){
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            redirectAttributes.addFlashAttribute("message","操作失败");
        } else {
            redirectAttributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",blogService.getBlog(id));
        return "admin/blogs_input";

    }

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
