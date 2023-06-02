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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

/**
 * User profile controller
 */
@Controller
public class ProfilesController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    /**
     * User profile handler
     * Show list of user tasks and main user information
     * @param model transfers data to templates
     */
    @GetMapping("profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        List<Task> tasks;
        if (Objects.equals(user.getRole().getName(), "employer")) {
            tasks = taskService.findByEmployerId(user.getId());
        } else {
            tasks = taskService.findByEmployeeId(user.getId());
        }

        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "profile";
    }

    /**
     * Other user profile handler
     * Show list of user tasks and main user information
     * @param id user id
     * @param model transfers data to templates
     */
    @GetMapping("user")
    public String userProfile(@RequestParam("id") String id, Model model) {
        User user = userService.find(Integer.parseInt(id));

        List<Task> tasks;
        if (Objects.equals(user.getRole().getName(), "employer")) {
            tasks = taskService.findByEmployerId(user.getId());
        } else {
            tasks = taskService.findByEmployeeId(user.getId());
        }

        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "user";
    }
}
