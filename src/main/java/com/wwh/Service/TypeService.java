package com.wwh.Service;

import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    //分类添加
    public Type addType(Type type) throws Exception {
        return typeRepository.save(type);
    }

    //分类删除
    public void deleteType(Long id) throws Exception {
        typeRepository.deleteById(id);
    }

    //分类名称修改
    public void updateType(Long id, String name) throws Exception {
        Type type = typeRepository.getOne(id);
        type.setName(name);
        typeRepository.save(type);
    }

    //分类列表
    public List<Type> getTypeList(Pageable pageable) throws Exception {
        Page<Type> page = typeRepository.findAll(pageable);
        return page.getContent();
    }




}
