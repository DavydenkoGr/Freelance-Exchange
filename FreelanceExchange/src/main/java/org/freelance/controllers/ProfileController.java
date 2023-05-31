package org.freelance.controllers;

import org.freelance.models.Task;
import org.freelance.models.User;
import org.freelance.services.TaskService;
import org.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @GetMapping("profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        if (user == null) {
            return "redirect:/authentication/login";
        }

        List<Task> tasks = taskService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "profile";
    }
}
