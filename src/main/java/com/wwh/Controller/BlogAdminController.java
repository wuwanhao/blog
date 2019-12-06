package com.wwh.Controller;

import com.wwh.DTO.BlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Service.BlogService;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogListItemVO;
import com.wwh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//博客后端管理接口
@RestController
@RequestMapping("/admin/blog")
public class BlogAdminController {


    @Autowired
    BlogService blogService;

    //增加博客
    @PostMapping("/add")
    public Result add(@Valid @RequestBody BlogDTO blogDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        blogService.addBlog(blogDTO);
        return new Result(200,"博客添加成功");
    }

    //获取列表
    @GetMapping("/list")
    public BlogListItemVO list(@Valid @RequestBody BlogQO blogQO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        return blogService.getBlogVOList(blogQO);
    }

    //获取详情
    @GetMapping("/{id}/detail")
    public BlogDetailVO getDetail(@PathVariable Long id) throws Exception {
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
    public Blog edit(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        return blogService.editBlog(id, blogDTO);
    }




}
