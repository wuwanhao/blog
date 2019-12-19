package com.wwh.Service;

import com.wwh.Entity.Type;
import com.wwh.Exception.NotFoundException;
import com.wwh.Repository.BlogRepository;
import com.wwh.Repository.TypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    BlogRepository blogRepository;

    //保存
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    //获得分类
    @Transactional
    public Type getType(Long id){
        return typeRepository.findById(id).get();

    }

    //分页查询
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).get();
        if (t == null) {
            throw new NotFoundException("该分类不存在");
        }
        BeanUtils.copyProperties(type, t);
        return  typeRepository.save(t);
    }

    //分类删除
    @Transactional
    public void deleteType(Long id) throws Exception {
        typeRepository.deleteById(id);
    }

    //获得按照大小由高到低排序的分类
    public List<Type> listTypeTop(Integer size){

        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    //所有分类
    public List<Type> list() {
        return typeRepository.findAll();
    }




}
