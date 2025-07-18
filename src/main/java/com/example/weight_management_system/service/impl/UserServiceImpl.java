package com.example.weight_management_system.service.impl;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.repository.UserMapper;
import com.example.weight_management_system.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserMapper userMapper, PasswordEncoder encoder) {
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    public void signup(MUser user) {
        user.setRoleCode(2);
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        userMapper.insertOne(user);
    }

    @Override
    public MUser getLoginUser(String email) {
        return userMapper.findLoginUser(email);
    }

    @Override
    public List<MUser> getUsers() {
        return userMapper.findUsers();
    }

    @Override
    public MUser getUser(Integer id) {
        return userMapper.findOneUser(id);
    }

    @Override
    public void updateUserPassword(Integer id, String password) {
        this.userMapper.updatePassword(id, password);
    }
}
