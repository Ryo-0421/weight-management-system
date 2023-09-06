package com.example.weight_management_system.controller;

import com.example.weight_management_system.form.UpdateWeightForm;
import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.model.MWeight;
import com.example.weight_management_system.service.UserService;
import com.example.weight_management_system.service.WeightService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("physicals")
public class UpdateWeightController {

    private final UserService userService;
    private final WeightService weightService;
    private final ModelMapper modelMapper;

    public UpdateWeightController(UserService userService, WeightService weightService, ModelMapper modelMapper) {
        this.userService = userService;
        this.weightService = weightService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("edit/{createdAt}")
    public String getUpdateWeight(@AuthenticationPrincipal UserDetails auth, @PathVariable("createdAt") String createdAt,
                                  @ModelAttribute UpdateWeightForm form, Model model) {
        String email = auth.getUsername();
        MUser user = this.userService.getLoginUser(email);

        LocalDateTime date = LocalDateTime.parse(createdAt);
        MWeight weight = this.weightService.findWeightByCreatedAt(date);

        form = this.modelMapper.map(weight, UpdateWeightForm.class);
        int userId = weight.getUserId();
        model.addAttribute("updateWeightForm", form);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", user.getName());

        return "updateWeight";
    }

    @PostMapping("edit/{createdAt}")
    public String postUpdateWeight(@AuthenticationPrincipal UserDetails auth, @PathVariable("createdAt") String createdAt,
                                   @ModelAttribute @Validated UpdateWeightForm form,
                                   BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return this.getUpdateWeight(auth, createdAt, form, model);
        }

        String email = auth.getUsername();
        MUser user = this.userService.getLoginUser(email);
        int userId = user.getId();
        redirectAttributes.addAttribute("userId", userId);

        this.weightService.editWeight(createdAt, form.getWeight(), form.getRecordedDate());

        return "redirect:/physicals/{userId}";
    }
}
