package com.example.weight_management_system.repository;

import com.example.weight_management_system.model.MUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertOne(MUser user);

    MUser findLoginUser(String email);

    List<MUser> findUsers();
}
