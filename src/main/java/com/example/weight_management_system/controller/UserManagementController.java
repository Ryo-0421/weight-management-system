package com.example.weight_management_system.controller;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserManagementController {

    private UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserManagement(@AuthenticationPrincipal UserDetails auth, Model model) {
        String email = auth.getUsername();
        MUser user = userService.getLoginUser(email);
        String userName = user.getName();
        model.addAttribute("userName", userName);
        return "userManagement";
    }
}
