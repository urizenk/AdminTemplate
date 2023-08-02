package com.example.controller;

import com.example.domain.Blog;
import com.example.domain.User;
import com.example.model.R;
import com.example.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yf
 * @create 2023-08-01 22:16
 */
@RestController
@RequestMapping("/blog")
@Api(tags = "博客控制器")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/getBlogs")
    @ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息")
    public R<List<Blog>> getAllBlogs(){
        List<Blog> blogs = blogService.getAllBlogs();
        return R.ok(blogs);
    }


}
