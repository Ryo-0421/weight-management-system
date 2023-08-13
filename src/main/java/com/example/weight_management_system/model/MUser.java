package com.example.weight_management_system.model;

import lombok.Data;

@Data
public class MUser {
    private Integer user_id;
    private Integer role_code;
    private String name;
    private String password;
    private String email;
}
