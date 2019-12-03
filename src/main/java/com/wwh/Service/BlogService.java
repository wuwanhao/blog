package com.wwh.Service;

import com.wwh.DTO.OpBlogDTO;
import com.wwh.Entity.Blog;
import com.wwh.QO.BlogQO;
import com.wwh.Repository.BlogRepository;
import com.wwh.VO.BlogNameVO;
import com.wwh.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

//    //构造查询对象
//    public Example<Blog> buildBlogExample(BlogQO blogQO) throws Exception {
//
//    }


    //列出所有博客(给前端)
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
    public Result addBlog(OpBlogDTO opBlogDTO) throws Exception {
        Blog newBlog = new Blog();
        BeanUtils.copyProperties(opBlogDTO, newBlog);
        newBlog.setCreateTime(new Date());
        blogRepository.save(newBlog);
        return new Result(200,"博客添加成功");
    }


    //删除博客
    public Result deleteBlog(Long id) throws Exception {
        blogRepository.deleteById(id);
        return new Result(200,"博客删除成功");
    }

    //修改博客
    public Result editBlog(Long id, OpBlogDTO opBlogDTO) throws Exception {
        Blog blog = blogRepository.findById(id).get();
        BeanUtils.copyProperties(opBlogDTO,blog);
        return new Result(200,"博客修改成功");
    }
}
