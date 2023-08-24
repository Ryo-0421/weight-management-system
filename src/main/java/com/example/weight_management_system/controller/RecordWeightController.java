package com.example.weight_management_system.controller;

import com.example.weight_management_system.form.RecordWeightForm;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/physicals")
public class RecordWeightController {

    private final UserService userService;
    private final WeightService weightService;
    private final ModelMapper modelMapper;

    public RecordWeightController(UserService userService, WeightService weightService, ModelMapper modelMapper) {
        this.userService = userService;
        this.weightService = weightService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public String getRecordWeight(@AuthenticationPrincipal UserDetails auth, @ModelAttribute RecordWeightForm form,
                                  Model model) {
        String email = auth.getUsername();
        MUser user = this.userService.getLoginUser(email);
        model.addAttribute("userName", user.getName());
        return "recordWeight";
    }

    @PostMapping("/create")
    public String postRecordWeight(@AuthenticationPrincipal UserDetails auth,
                                   @ModelAttribute @Validated RecordWeightForm form,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return getRecordWeight(auth, form, model);
        }

        MWeight weight = this.modelMapper.map(form, MWeight.class);
        this.weightService.recordWeight(weight);
        return "redirect:/home";
    }
}
