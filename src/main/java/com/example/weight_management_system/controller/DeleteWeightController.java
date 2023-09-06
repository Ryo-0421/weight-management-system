package com.example.weight_management_system.controller;

import com.example.weight_management_system.service.WeightService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("physicals")
public class DeleteWeightController {

    private final WeightService weightService;
    private final HttpSession session;

    public DeleteWeightController(WeightService weightService, HttpSession session) {
        this.weightService = weightService;
        this.session = session;
    }

    @GetMapping("delete/{createdAt}")
    public String getDeleteWeight(@PathVariable("createdAt") String createdAt, RedirectAttributes redirectAttributes) {
        int userId = (int)this.session.getAttribute("userId");
        redirectAttributes.addAttribute("userId", userId);
        this.weightService.deleteOneWeight(createdAt);
        return "redirect:/physicals/{userId}";
    }
}
