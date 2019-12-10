//package com.wwh.Controller;
//
//import com.wwh.Service.TypeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/admin")
//public class Type01Controller {
//
//
//    @Autowired
//    TypeService typeService;
//
//
//    @GetMapping("/types")
//    public String type(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) throws Exception {
//
//
//        model.addAttribute("page",typeService.getTypeList(pageable));
//        return "admin/types";
//    }
//}
