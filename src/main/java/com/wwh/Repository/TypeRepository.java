package com.wwh.Repository;

import com.wwh.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAll();

    //分类模糊搜索
    @Query(value = "select * from type where type.id like %?1%", nativeQuery = true)
    List<Type> search(String name);

}
