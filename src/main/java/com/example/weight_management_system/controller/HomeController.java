package com.example.weight_management_system.controller;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final UserService userService;
    private final HttpSession session;

    public HomeController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @GetMapping("/home")
    public String getHome(@AuthenticationPrincipal UserDetails auth, Model model) {
        int userId = (int)this.session.getAttribute("userId");
        String email = auth.getUsername();
        MUser user = userService.getLoginUser(email);
        String userName = user.getName();
        model.addAttribute("userName", userName);
        model.addAttribute("userId", userId);
        return "home";
    }
}
