package com.example.service.impl;

import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yf
 * @create 2023-07-22 19:45
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //在模版中配置的用户，可以根据实际项目连接数据库查询
        com.example.domain.User user =  userMapper.selectById(1);
        return User.builder()
                .username(user.getNickName())
                .password("123456")
                .roles("admin")
                .build();
    }
}
