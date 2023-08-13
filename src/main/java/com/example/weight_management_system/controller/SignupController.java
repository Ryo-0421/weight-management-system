package com.example.weight_management_system.controller;

import com.example.weight_management_system.form.SignupForm;
import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SignupController {

    private UserService userService;
    private ModelMapper modelMapper;

    public SignupController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("signup")
    public String getSignup(@ModelAttribute SignupForm form) {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return getSignup(form);
        }

        log.info(form.toString());

        MUser user = modelMapper.map(form, MUser.class);
        this.userService.signup(user);
        return "redirect:/login";
    }
}
