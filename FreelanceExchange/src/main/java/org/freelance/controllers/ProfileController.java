package org.freelance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @GetMapping("profile")
    public String profile(Model model, @RequestParam("id") long id) {
        // Поиск юзера
        model.addAttribute(id);
        return "profile";
    }
}
