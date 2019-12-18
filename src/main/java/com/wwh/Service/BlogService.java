package com.wwh.Service;

import com.wwh.Entity.Blog;
import com.wwh.Exception.NotFoundException;
import com.wwh.Repository.BlogRepository;
import com.wwh.VO.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    //取得博客信息
    public Blog getBlog(Long id){
        return blogRepository.findById(id).get();
    }

    //获取博客列表
    public Page<Blog> listBlog(Pageable pageable){
        return blogRepository.findAll(pageable);
    }

    //获取搜索博客列表
    public Page<Blog> listBlog(Pageable pageable, String query){
        return blogRepository.findByQuery(query, pageable);
    }


    //获得推荐博客列表
    public List<Blog> listRecommendBlogTop(Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"update_time");
        Pageable pageable = new PageRequest(0,size,sort);
        return blogRepository.recommendBlog(pageable);
    }




    //保存博客
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            System.out.println("博客创建时间：" + blog.getCreateTime());
            blog.setUpdateTime(new Date());
        }


        return blogRepository.save(blog);
    }

    //删除博客
    public void deleteBlog(Long id) throws Exception {
        blogRepository.deleteById(id);
    }

    //更新博客
    public Blog updateBlog(Long id, Blog blog) throws Exception {
        Blog b = blogRepository.findById(id).get();
        if (b == null) {
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(blog, b);
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    //获取博客详情
    public BlogDetailVO getDetail(Long id) throws Exception {
        Blog blog = blogRepository.findById(id).orElse(null);

        if (blog == null) {
            throw new Exception("找不到该博客");
        }

        BlogDetailVO blogDetailVO = new BlogDetailVO();
        BeanUtils.copyProperties(blog, blogDetailVO);

        return blogDetailVO;

    }




}
