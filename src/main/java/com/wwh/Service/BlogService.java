package com.wwh.Service;

import com.wwh.DTO.BlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import com.wwh.Entity.User;
import com.wwh.Exception.NotFoundException;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.VO.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public Blog getBlog(Long id){
        return blogRepository.findById(id).get();
    }

    public Page<Blog> listBlog(Pageable pageable){

        return blogRepository.findAll(pageable);

    }


//    public Page<Blog> listBlog(Pageable pageable, Blog blog){
//
//        //动态查询
//        return blogRepository.findAll(new Specification<Blog>() {
//            @Override
//            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> list = new ArrayList<>();
//                if (!"".equals(blog.getTitle()) && blog.getTitle() != null ) {
//                    list.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
//                }
//                if (blog.getType().getId() != null) {
//                    list.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getType().getId()));
//                }
//                if (blog.isRecommend()) {
//                    list.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
//                }
//                query.where(list.toArray(new Predicate[list.size()]));
//                return null;
//            }
//        }, pageable);
//
//    }



    //保存
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

    //修改博客
    public Blog updateBlog(Long id, Blog blog) throws Exception {
        Blog b = blogRepository.findById(id).get();
        if (b == null) {
            throw new NotFoundException("博客不存在");
        }
        BeanUtils.copyProperties(blog, b);
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
    //获取博客（测试）
    public Blog getBlogDetail(Long id) throws Exception{
        return blogRepository.findById(id).get();
    }


    //博客搜索
    public List<BlogNameVO> search(String keyWord) throws Exception {
        List<Blog> blogs = blogRepository.searchBlog(keyWord);
        List<BlogNameVO> blogNameVOS = new ArrayList<>();

        for (int i=0; i<blogs.size(); i++) {
            BlogNameVO blogNameVO = new BlogNameVO();
            BeanUtils.copyProperties(blogs.get(i), blogNameVO);
            blogNameVOS.add(blogNameVO);
        }

        return blogNameVOS;
    }


    //推荐博客
    public List<BlogNameVO> recommend() throws Exception {
        List<Blog> blogs = blogRepository.recommendBlog();
        List<BlogNameVO> blogNameVOS = new ArrayList<>();


        for (int i=0; i<blogs.size(); i++) {
            BlogNameVO blogNameVO = new BlogNameVO();
            BeanUtils.copyProperties(blogs.get(i), blogNameVO);
            blogNameVOS.add(blogNameVO);
        }

        return blogNameVOS;
    }
}
