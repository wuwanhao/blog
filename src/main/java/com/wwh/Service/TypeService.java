package com.wwh.Service;

import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    BlogRepository blogRepository;

    //添加分类
    public void addType(String name) throws Exception {
        Type type = new Type();
        type.setName(name);
        typeRepository.save(type);
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

    //分类查询
    public Type getType(Long id) throws Exception {
        Type type = typeRepository.getOne(id);
        return type;
    }


    //分类列表
    public List<Type> getTypeList() throws Exception {
        List<Type> typeList = typeRepository.findAll();
        return typeList;
    }

    //获取分类列表+分类下文章个数
    public List<TypeNameVO> getTypeListAndNum() throws Exception {
        //查询分类名
        List<Type> types = typeRepository.findAll();
        //获取blog
        List<Blog> blogs = blogRepository.findAll();

        List<TypeNameVO> typeNameVOList = new ArrayList<>();

        for (int i=0; i<types.size(); i++) {
            TypeNameVO typeNameVO = new TypeNameVO();
            //获取该分类下文章个数
            int blogNumOfType = blogRepository.getBlogOfType(types.get(i).getId()).size();
            //封装
            typeNameVO.setNumberOfType(blogNumOfType);
            typeNameVO.setType(types.get(i));

            typeNameVOList.add(typeNameVO);
        }

        return typeNameVOList;

    }

    //获取该分类下文章
    public List<BlogNameVO> getBlogsOfTypeByTypeId(Long typeId) throws Exception {

        List<Blog> blogList = blogRepository.getBlogOfType(typeId);

        List<BlogNameVO> blogNameVOList = new ArrayList<>();

        for (int i=0; i<blogList.size(); i++) {
            BeanUtils.copyProperties(blogList.get(i), blogNameVOList.get(i));
        }

        return blogNameVOList;
    }



//    //分类搜索+获取分类下的文章
////    public List<TypeSearchResultVO> search(String keyWord) throws Exception {
////        List<Type> typeList = typeRepository.searchType(keyWord);
////
////        List<TypeSearchResultVO> searchResultVOList = new ArrayList<>();
////
////        //遍历分类列表，返回每种分类下的文章
////        for (int i=0; i<typeList.size(); i++) {
////
////            //第i种分类下的文章列表
////            List<BlogNameVO> list = this.getBlogsOfTypeByTypeId(typeList.get(i).getId());
////            searchResultVOList.add().setBlogNameVOS(list);
////        }
////        return searchResultVOList;
////    }




}
