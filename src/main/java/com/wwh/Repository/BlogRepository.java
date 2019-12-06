package com.wwh.Repository;

import com.wwh.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    //某一分类下文章个数
    @Query(value = "select * from blog where blog.type_id = ?1", nativeQuery = true)
    List<Blog> getBlogOfType(Long id);
}
