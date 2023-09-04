package com.example.weight_management_system.controller;

import com.example.weight_management_system.model.MUser;
import com.example.weight_management_system.model.MWeight;
import com.example.weight_management_system.service.UserService;
import com.example.weight_management_system.service.WeightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/physicals")
public class ViewWeightController {

    private final UserService userService;
    private final WeightService weightService;

    public ViewWeightController(UserService userService, WeightService weightService) {
        this.userService = userService;
        this.weightService = weightService;
    }

    @GetMapping("/{userId}")
    public String getViewWeight(@PathVariable("userId") int userId, @AuthenticationPrincipal UserDetails auth,
                                Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        String email = auth.getUsername();
        MUser user = this.userService.getLoginUser(email);
        model.addAttribute("userName", user.getName());

        Pageable pageable = PageRequest.of(page, 10);
        Page<MWeight> pageList = this.weightService.getWeights(userId, pageable);
        List<MWeight> weights = pageList.getContent();

        model.addAttribute("page", pageList);
        model.addAttribute("weights", weights);
        return "viewWeight";
    }
}
