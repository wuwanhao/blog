package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.Service.BlogService;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogNameItemVO;
import com.wwh.VO.BlogNameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "博客前台")
@RequestMapping("/blog")
public class BlogController {


    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    //findAll
    @GetMapping("/findAll")
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    //列出博客
    @ApiOperation("列出博客")
    @GetMapping("/list")
    public BlogNameItemVO getBlogNameList(@Valid @RequestBody BlogQO blogQO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasFieldErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        return blogService.getBlogNameList(blogQO);
    }

    //查看博客详细信息
    @ApiOperation("查看博客详细信息")
    @GetMapping("/{id}/detail")
    public BlogDetailVO getDetail(@PathVariable Long id) throws Exception {
        return blogService.getDetail(id);
    }

    //搜索博客
    @ApiOperation("搜索博客")
    @GetMapping("/search")
    public List<BlogNameVO> search(String keyWord) throws Exception {
        return blogService.search(keyWord);
    }

    @ApiOperation("推荐博客")
    @GetMapping("/recommend")
    public List<BlogNameVO> recommend() throws Exception {
        return blogService.recommend();
    }



}
