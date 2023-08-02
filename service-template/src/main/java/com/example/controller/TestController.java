package com.example.controller;

import com.example.model.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yf
 * @create 2023-08-01 17:35
 */
@RestController
public class TestController {

    @GetMapping(("/hello"))
    public R<String> test(){
        return R.ok("hello world");
    }
}
