package com.wwh.Repository;

import com.wwh.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    //分类模糊搜索
    @Query(value = "select * from Type as type where type.name like %?1%", nativeQuery = true)
    List<Type> searchType(String name);
}
