package com.example.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
/**
@author yf
@create 2023-08-02 12:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
