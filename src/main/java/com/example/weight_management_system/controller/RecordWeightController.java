package com.example.weight_management_system.controller;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/physicals")
public class RecordWeightController {

    private UserService userService;

    public RecordWeightController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String getRecordWeight(@AuthenticationPrincipal UserDetails auth, Model model) {
        String email = auth.getUsername();
        MUser user = userService.getLoginUser(email);
        model.addAttribute("userName", user.getName());
        return "recordWeight";
    }
}
