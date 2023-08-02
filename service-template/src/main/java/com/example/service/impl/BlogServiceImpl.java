package com.example.service.impl;

import com.example.domain.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.domain.Blog;
import com.example.mapper.BlogMapper;
import com.example.service.BlogService;

import java.util.List;

/**
@author yf
@create 2023-08-01 22:14
*/
@Service
public class BlogServiceImpl implements BlogService{

    @Resource
    private BlogMapper blogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Blog record) {
        return blogMapper.insert(record);
    }

    @Override
    public int insertSelective(Blog record) {
        return blogMapper.insertSelective(record);
    }

    @Override
    public Blog selectByPrimaryKey(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return blogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.getAllBlogs();
    }

}
