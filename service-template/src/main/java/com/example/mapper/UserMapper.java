package com.example.mapper;

import com.example.domain.User;

/**
@author yf
@create 2023-08-01 22:14
*/
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}