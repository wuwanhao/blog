package com.wwh.Repository;

import com.wwh.Entity.Blog;
import com.wwh.VO.BlogNameVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    //某一分类下文章个数
    @Query(value = "select * from blog where blog.type_id = ?1", nativeQuery = true)
    List<Blog> getBlogOfType(Long id);


    //博客模糊搜索
    @Query(value = "select * from blog where blog.title like %?1%", nativeQuery = true)
    List<Blog> searchBlog(String keyWord);


    //获得推荐博客
    @Query(value = "select * from blog where blog.recommend = 1", nativeQuery = true)
    List<Blog> recommendBlog();
}
