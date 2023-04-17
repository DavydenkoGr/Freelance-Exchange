package org.freelance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("view")
public class TempExample {
    @GetMapping("test")
    public String test(Model model, @RequestParam("name") String name) {
        model.addAttribute("name", name);
        return "test";
    }
}
