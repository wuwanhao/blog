package com.wwh.Service;

import com.wwh.DTO.BlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogListVO;
import com.wwh.VO.BlogNameVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    //列出博客（管理界面）
    public List<BlogListVO> getBlogVOList(BlogQO blogQO) throws Exception {

        // 分页
        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        Pageable pageable = PageRequest.of(blogQO.getPage(), blogQO.getSize(), sort);

        List<Blog> blogs  = blogRepository.findAll(pageable).getContent();

        //映射VO
        List<BlogListVO> blogVOList = blogs.stream().map((blog) -> {
            BlogListVO blogListVO = new BlogListVO();
            BeanUtils.copyProperties(blog, blogListVO);
            return blogListVO;
        }).collect(Collectors.toList());
        return blogVOList;

    }


    //列出所有博客(展示界面)
    public List<BlogNameVO> getBlogNameList(BlogQO blogQO) throws Exception {

        // 分页
        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        Pageable pageable = PageRequest.of(blogQO.getPage(), blogQO.getSize(), sort);

        List<Blog> blogs  = blogRepository.findAll(pageable).getContent();

        //映射VO
        List<BlogNameVO> blogNameVOList = blogs.stream().map((blog) -> {
            BlogNameVO blogNameVO = new BlogNameVO();
            BeanUtils.copyProperties(blog, blogNameVO);
            return blogNameVO;
        }).collect(Collectors.toList());
        return blogNameVOList;

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
