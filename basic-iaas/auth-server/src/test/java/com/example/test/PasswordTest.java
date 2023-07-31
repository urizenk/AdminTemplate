package com.example.test;

import org.junit.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author yf
 * @create 2023-07-21 23:36
 */
public class PasswordTest {

    @Test
    public void test(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("hhhhh")
                .password("123456").
                roles("user").build();

        System.out.println(userDetails.getPassword());
    }
}
