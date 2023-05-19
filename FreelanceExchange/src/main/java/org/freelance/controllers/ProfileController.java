package org.freelance.controllers;

import org.freelance.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ProfileController {
    @GetMapping("profile")
    public String profile(Model model) {
        return "profile";
    }
}
