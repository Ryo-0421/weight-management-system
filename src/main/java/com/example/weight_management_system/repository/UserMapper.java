package com.example.weight_management_system.repository;

import com.example.weight_management_system.model.MUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertOne(MUser user);
}
