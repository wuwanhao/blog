package com.wwh.Service;

import com.wwh.DTO.BlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.VO.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;


    //构造查询对象
    public Example<Blog> buildBlogExample(BlogQO blogQO) throws Exception {
        Blog blog = new Blog();

        BeanUtils.copyProperties(blogQO, blog);

        return Example.of(blog);
    }

    //列出博客（管理界面）
    public BlogListItemVO getBlogVOList(BlogQO blogQO) throws Exception {

        // 分页
        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        Pageable pageable = PageRequest.of(blogQO.getPage(), blogQO.getSize(), sort);

        //构造例子
        Example<Blog> blogExample = this.buildBlogExample(blogQO);

        //查询
        Page<Blog> blogs = blogRepository.findAll(blogExample, pageable);
        List<Blog> list = blogs.getContent();
        Long total = blogs.getTotalElements();

        //映射VO
        List<BlogListVO> blogVOList = list.stream().map((blog) -> {
            BlogListVO blogListVO = new BlogListVO();
            BeanUtils.copyProperties(blog, blogListVO);
            return blogListVO;
        }).collect(Collectors.toList());

        BlogListItemVO blogListItemVO = new BlogListItemVO();
        blogListItemVO.setBlogListVOList(blogVOList);
        blogListItemVO.setNumber(total);

        return blogListItemVO;

    }


    //列出所有博客(展示界面)
    public BlogNameItemVO getBlogNameList(BlogQO blogQO) throws Exception {

        // 分页
        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        Pageable pageable = PageRequest.of(blogQO.getPage(), blogQO.getSize(), sort);

        //构造例子
        Example<Blog> blogExample = this.buildBlogExample(blogQO);

        //查询
        Page<Blog> page = blogRepository.findAll(blogExample, pageable);
        List<Blog> list = page.getContent();
        Long total = page.getTotalElements();

        //映射VO
        List<BlogNameVO> blogNameVOList = list.stream().map((blog) -> {
            BlogNameVO blogNameVO = new BlogNameVO();
            BeanUtils.copyProperties(blog, blogNameVO);
            return blogNameVO;
        }).collect(Collectors.toList());


        BlogNameItemVO blogNameItemVO = new BlogNameItemVO();
        blogNameItemVO.setBlogNameVOList(blogNameVOList);
        blogNameItemVO.setNumber(total);
        return blogNameItemVO;

    }

    //添加新博客
    public Blog addBlog(BlogDTO blogDTO) throws Exception {
        Blog newBlog = new Blog();
        BeanUtils.copyProperties(blogDTO, newBlog);
        newBlog.setCreateTime(new Date());
        return blogRepository.save(newBlog);
    }


    //删除博客
    public void deleteBlog(Long id) throws Exception {
        blogRepository.deleteById(id);
    }

    //修改博客
    public Blog editBlog(Long id, BlogDTO blogDTO) throws Exception {
        Blog blog = blogRepository.findById(id).get();
        BeanUtils.copyProperties(blogDTO,blog);
        return blog;
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
