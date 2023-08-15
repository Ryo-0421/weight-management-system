package com.example.weight_management_system.service.impl;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.repository.UserMapper;
import com.example.weight_management_system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void signup(MUser user) {
        user.setRole_code(2);
        userMapper.insertOne(user);
    }

    @Override
    public MUser getLoginUser(String email) {
        return userMapper.findLoginUser(email);
    }
}
