package com.example.weight_management_system.model;

import lombok.Data;

@Data
public class MUser {
    private Integer id;
    private Integer roleCode;
    private String name;
    private String password;
    private String email;
}
