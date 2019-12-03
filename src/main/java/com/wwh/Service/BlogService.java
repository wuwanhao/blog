package com.wwh.Service;

import com.wwh.DTO.AddBlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.Repository.BlogRepository;
import com.wwh.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;


    //列出所有博客
    public List<Blog> listBlog() throws Exception {
        return blogRepository.findAll();
    }

    //添加新博客
    public Result addBlog(AddBlogDTO addBlogDTO) throws Exception {
        Blog newBlog = new Blog();
        BeanUtils.copyProperties(addBlogDTO, newBlog);
        newBlog.setCreateTime(new Date());
        blogRepository.save(newBlog);
        return new Result(200,"博客添加成功");
    }

    //删除博客
    public
    //修改博客

}
