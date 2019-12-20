package com.wwh.Controller;

import com.wwh.DTO.AddUserDTO;
import com.wwh.Entity.Role;
import com.wwh.Entity.User;
import com.wwh.Repository.RoleRepository;
import com.wwh.Service.UserService;
import com.wwh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    //添加用户
//    @PostMapping("/add")
//    public Result<?> addUser(@RequestBody AddUserDTO addUserDTO) throws Exception {
//
//        //添加角色
////        List<Role> roles = new ArrayList<>();
//
//        Role role = roleRepository.findByName("USER");
//        System.out.println("角色：" + role);
////        roles.add(role);
////        System.out.println("333"+role);
//
//        User newUser = new User();
//        newUser.setUsername(addUserDTO.getUsername());
//        System.out.println("用户名：" + addUserDTO.getUsername());
//
//        newUser.setPassword(addUserDTO.getPassword());
//        System.out.println("密码：" + addUserDTO.getPassword());
//        newUser.setRole(role);
//
//
//
//        //保存
//        User user1 = userService.addUser(newUser);
//
//        System.out.println("666" + user1);
//        return new Result(200,"添加成功");
//
//    }
}
