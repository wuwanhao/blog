package com.wwh.Controller;

import com.wwh.Entity.Blog;
import com.wwh.Repository.BlogRepository;
import com.wwh.Service.BlogService;
import com.wwh.Service.TypeService;
import com.wwh.Service.UserService;
import com.wwh.VO.BlogDetailVO;
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

    //查看博客详细信息
    @ApiOperation("查看博客详细信息")
    @GetMapping("/{id}/detail")
    public String getDetail(@PathVariable Long id, Model model) throws Exception {
        BlogDetailVO blogDetailVO = blogService.getDetail(id);
        System.out.println("999" + blogDetailVO);
        model.addAttribute("title", blogDetailVO.getTitle());
        model.addAttribute("content", blogDetailVO.getContent());
        model.addAttribute("firstPicture", blogDetailVO.getFirstPicture());
        model.addAttribute("flag", blogDetailVO.getFlag());
        model.addAttribute("views", blogDetailVO.getViews());
        model.addAttribute("createTime", blogDetailVO.getCreateTime());
        model.addAttribute("type", blogDetailVO.getType().getName());
        model.addAttribute("username", blogDetailVO.getUser().getUsername());
        System.out.println("666" + blogDetailVO.getUser().getUsername());
        model.addAttribute("userAvatar",blogDetailVO.getUser());

        return "blog";


    }



}
