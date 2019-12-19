package com.wwh.Controller;

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
    @PostMapping("/add")
    public Result<?> addUser(@RequestBody User user) throws Exception {

        //添加角色
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByName("USER");
        roles.add(role);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        System.out.println("用户名：" + user.getUsername());

        newUser.setPassword(user.getPassword());
        System.out.println("密码：" + user.getPassword());
        newUser.setRoles(roles);
        System.out.println();


        //保存
        User user1 = userService.addUser(newUser);

        System.out.println("666" + user1);
        return new Result(200,"添加成功");

    }
}
