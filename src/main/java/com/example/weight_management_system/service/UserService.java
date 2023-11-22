package com.example.weight_management_system.service;

import com.example.weight_management_system.model.MUser;

import java.util.List;

public interface UserService {

    void signup(MUser user);

    MUser getLoginUser(String email);

    List<MUser> getUsers();

    MUser getUser(Integer id);

    void updateUserPassword(Integer id, String password);
}
