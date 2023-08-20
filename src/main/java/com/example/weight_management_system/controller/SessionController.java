package com.example.weight_management_system.controller;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SessionController {

    private UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/session")
    public String getSession(@AuthenticationPrincipal UserDetails auth, HttpServletRequest request) {
        String email = auth.getUsername();
        MUser user = userService.getLoginUser(email);
        int userId = user.getId();

        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);

        return "redirect:/home";
    }
}
