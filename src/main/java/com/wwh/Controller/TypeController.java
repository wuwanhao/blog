package com.wwh.Controller;

import com.wwh.Entity.Type;
import com.wwh.Repository.TypeRepository;
import com.wwh.Service.TypeService;
import com.wwh.VO.BlogNameVO;
import com.wwh.VO.TypeNameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "分类前台")
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    TypeService typeService;

    @Autowired


    //分类总数（分类页面右上角个数）
    @ApiOperation("分类总数（分类页面右上角个数）")
    @GetMapping("/number")
    public Integer getTypeNumber() throws Exception {
        return typeRepository.findAll().size();
    }


    //分类查询
    @ApiOperation("分类查询")
    @GetMapping("/{id}/detail")
    public Type getType(@PathVariable Long id) throws Exception {
        return typeService.getType(id);
    }


    //获取分类列表+分类下文章个数
    @ApiOperation("获取分类列表+分类下文章个数")
    @GetMapping("/getTypeListAndNum")
    public List<TypeNameVO> getTypeListAndNum() throws Exception {
        return typeService.getTypeListAndNum();
    }

    //获取某分类下文章
    @ApiOperation("获取某分类下文章")
    @GetMapping("/{typeId}/getBlogsByTypeId")
    public List<BlogNameVO> getBlogsOfTypeByTypeId(@PathVariable Long typeId) throws Exception {
        return typeService.getBlogsOfTypeByTypeId(typeId);
    }


    //分类分页



}
