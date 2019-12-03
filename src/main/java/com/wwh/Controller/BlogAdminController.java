package com.wwh.Controller;

import com.wwh.DTO.OpBlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Service.BlogService;
import com.wwh.VO.BlogListVO;
import com.wwh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


//博客后端管理接口
@RestController
@RequestMapping("/admin/blog")
public class BlogAdminController {


    @Autowired
    BlogService blogService;

    //增加博客
    @PostMapping("/add")
    public Result add(@Valid @RequestBody OpBlogDTO opBlogDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        blogService.addBlog(opBlogDTO);
        return new Result(200,"博客添加成功");
    }

    //获取列表
    @GetMapping("/list")
    public List<BlogListVO> list(@Valid BlogQO blogQO,BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        return blogService.getBlogVOList(blogQO);
    }

    //获取详情
    @GetMapping("/{id}/detail")
    public Blog getDetail(@PathVariable Long id) throws Exception {
        return blogService.getDetail(id);
    }

    //删除博客
    @DeleteMapping("/{id}/delete")
    public Result deleteBlog(@PathVariable Long id) throws Exception {
        blogService.deleteBlog(id);
        return new Result(200,"删除成功");
    }

    //更改博客
    @PostMapping("{id}/modify")
    public Blog edit(@PathVariable Long id, @Valid @RequestBody OpBlogDTO opBlogDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        return blogService.editBlog(id, opBlogDTO);
    }




}
