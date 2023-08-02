package com.example.mapper;

import com.example.domain.Blog;

import java.util.List;

/**
@author yf
@create 2023-08-01 22:14
*/
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);



    List<Blog> getAllBlogs();
}