package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.Entity.Type;
import com.wwh.Repository.BlogRepository;
import com.wwh.Service.BlogService;
import com.wwh.Service.TypeService;
import com.wwh.Service.UserService;
import com.wwh.VO.BlogDetailVO;
import com.wwh.VO.BlogQuery;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model) throws Exception {
        //拿到分页数据
        model.addAttribute("page", blogService.listBlog(pageable));

        System.out.println("666" + blogService.listBlog(pageable).getContent());
        model.addAttribute("types", typeService.listTypeTop(6));
        System.out.println("999" + typeService.listTypeTop(6));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(4));
        return "index";
    }

    @RequestMapping("/types")
    public String type() {
        return "types";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/auth/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    //登陆成功后处理
    @RequestMapping(value = "/admin/index")
    public String successfulPage() {
        return "admin/index";
    }



    @PostMapping("/search")
    public String search(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                     @RequestParam String query, Pageable pageable, Model model) throws Exception {
        model.addAttribute("page", blogService.listBlog(pageable, query));
        System.out.println("666" + blogService.listBlog(pageable, query).getContent());
        model.addAttribute("query", query);
        return "search";
    }


    //博客详情
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getAndConvert(id));
        return "blog";
    }




    //分类页面
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 6, sort = {"updateTime"}, direction = Sort.Direction.DESC)
            Pageable pageable,@PathVariable Long id, Model model) {

        List<Type> typeList = typeService.listTypeTop(10000);
        if (id == -1) {
            id = typeList.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",typeList);
        System.out.println("999" + typeList);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);

        return "types";
    }



}
