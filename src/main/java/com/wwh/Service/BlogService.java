package com.wwh.Service;

import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import com.wwh.Exception.NotFoundException;
import com.wwh.Repository.BlogRepository;
import com.wwh.VO.*;
import com.wwh.utils.BeanUtil;
import com.wwh.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.Predicate;


@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    //取得博客信息
    public Blog getBlog(Long id){
        return blogRepository.findById(id).get();
    }

    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }


    //获取博客信息和转换
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.findById(id).get();
        if (blog == null) {
            throw new NotFoundException("博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;

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
        BeanUtils.copyProperties(blog, b, BeanUtil.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    //博客点赞
    public Integer starBlog(Long id){
        Blog blog = blogRepository.getOne(id);
        blog.setStar(blog.getStar() + 1);
        Blog blog1 = blogRepository.save(blog);
        return blog1.getStar();
    }






}
