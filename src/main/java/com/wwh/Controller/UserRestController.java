package com.wwh.Controller;

import com.wwh.Entity.Role;
import com.wwh.Entity.User;
import com.wwh.Repository.RoleRepository;
import com.wwh.Service.UserService;
import com.wwh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    //添加用户
    @PostMapping("/add")
    public Result<?> addUser(User user) throws Exception {

        //添加角色
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByName("USER");
        roles.add(role);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setNickname(user.getNickname());
        newUser.setRoles(roles);

        //保存
        userService.addUser(newUser);
        return new Result(200,"添加成功");

    }
}
