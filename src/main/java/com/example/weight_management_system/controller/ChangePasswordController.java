package com.example.weight_management_system.controller;

import com.example.weight_management_system.form.ChangePasswordForm;
import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ChangePasswordController {

    private final UserService userService;
    private final HttpSession session;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/changePassword")
    public String getPassword(@ModelAttribute ChangePasswordForm changePasswordForm) {
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String postPassword(@ModelAttribute @Validated ChangePasswordForm changePasswordForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "changePassword";
        }

        Integer id = (int)session.getAttribute("userId");
        MUser user = this.userService.getUser(id);
        String storedPassword = user.getPassword();

        if (!passwordEncoder.matches(changePasswordForm.getCurrentPassword(), storedPassword)) {
            model.addAttribute("PasswordMessage", "登録されているパスワードが違います。");
            return getPassword(changePasswordForm);
        }

        String newPasswordHash = passwordEncoder.encode(changePasswordForm.getNewPassword());
        this.userService.updateUserPassword(id, newPasswordHash);
        return "redirect:/home";
    }
}
