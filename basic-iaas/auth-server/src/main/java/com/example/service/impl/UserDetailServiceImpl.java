package com.example.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author yf
 * @create 2023-07-22 19:45
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //在模版中配置的用户，可以根据实际项目连接数据库查询

        return User.builder()
                .username("admin")
                .password("123456")
                .roles("admin")
                .build();
    }
}
