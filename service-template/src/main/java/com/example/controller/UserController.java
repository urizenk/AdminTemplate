package com.example.controller;

import com.example.domain.User;
import com.example.model.R;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yf
 * @create 2023-08-01 22:34
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户控制器")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    public R<User> getUserById(@PathVariable Long id){
        User user = userService.selectByPrimaryKey(id);
        return R.ok(user);
    }
}
