package com.wwh.Service;

import com.sun.xml.bind.v2.TODO;
import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import com.wwh.Exception.NotFoundException;
import com.wwh.Repository.BlogRepository;
import com.wwh.Repository.TypeRepository;
import com.wwh.VO.BlogNameVO;
import com.wwh.VO.TypeNameVO;
import com.wwh.VO.TypeSearchResultVO;
import com.wwh.utils.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    //获得所有分类
    public List<Type> list(){
        return typeRepository.findAll();
    }

//    //分类名称修改
//    public void updateType(Long id, String name) throws Exception {
//        Type type = typeRepository.getOne(id);
//        type.setName(name);
//        typeRepository.save(type);
//
//    }
//
//
//
//
//    //分类列表分页
//    public Page<Type> getTypeList(Pageable pageable) throws Exception {
//        return typeRepository.findAll(pageable);
//    }
//
//    //获取分类列表+分类下文章个数
//    public List<TypeNameVO> getTypeListAndNum() throws Exception {
//        //查询分类名
//        List<Type> types = typeRepository.findAll();
//        //获取blog
//        List<Blog> blogs = blogRepository.findAll();
//
//        List<TypeNameVO> typeNameVOList = new ArrayList<>();
//
//        for (int i=0; i<types.size(); i++) {
//            TypeNameVO typeNameVO = new TypeNameVO();
//            //获取该分类下文章个数
//            int blogNumOfType = blogRepository.getBlogOfType(types.get(i).getId()).size();
//
//            //封装
//            typeNameVO.setNumberOfType(blogNumOfType);
//            typeNameVO.setType(types.get(i));
//
//            typeNameVOList.add(typeNameVO);
//        }
//
//        return typeNameVOList;
//
//    }
//
//    //获取该分类下文章
//    public List<BlogNameVO> getBlogsOfTypeByTypeId(Long typeId) throws Exception {
//
//        List<Blog> blogList = blogRepository.getBlogOfType(typeId);
//
//        List<BlogNameVO> blogNameVOList = new ArrayList<>();
//
//        for (int i=0; i<blogList.size(); i++) {
//            BlogNameVO blogNameVO = new BlogNameVO();
//            BeanUtils.copyProperties(blogList.get(i), blogNameVO);
//            blogNameVOList.add(blogNameVO);
//        }
//
//        return blogNameVOList;
//    }
//
//
//
//    //分类搜索+获取分类下的文章(有问题)
//    public List<TypeSearchResultVO> search(String keyWord) throws Exception {
//        List<Type> typeList = typeRepository.search(keyWord);
//        System.out.println("搜索到的分类列表： " + typeList);
//
//        //总表
//        List<TypeSearchResultVO> searchResultVOList = new ArrayList<>();
//
//        //遍历分类列表，返回每种分类下的文章
//        for (int i=0; i<typeList.size(); i++) {
//            TypeSearchResultVO typeSearchResultVO = new TypeSearchResultVO();
//
//            //第i种分类下的文章列表
//            List<BlogNameVO> list = this.getBlogsOfTypeByTypeId(typeList.get(i).getId());
//            System.out.println("第" + (i+1) + "种分类下的文章列表： " + typeList);
//            typeSearchResultVO.setBlogNameVOS(list);
//            typeSearchResultVO.setNumber(list.size());
//
//            searchResultVOList.add(typeSearchResultVO);
//        }
//        System.out.println("每种分类下的文章： " +  searchResultVOList);
//        return searchResultVOList;
//    }




}
