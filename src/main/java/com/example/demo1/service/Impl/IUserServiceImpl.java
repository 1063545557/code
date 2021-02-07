package com.example.demo1.service.Impl;

import com.example.demo1.entity.User;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List selectUser() {
        return userMapper.selectUser();
    }

}
