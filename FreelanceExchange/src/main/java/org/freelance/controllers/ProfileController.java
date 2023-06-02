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
public class ProfileController {
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
    public String profile(@RequestParam(name = "id", required = false) String id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.find(authentication.getName());

        User user;
        if (id != null) {
            user = userService.find(Integer.parseInt(id));
        } else {
            user = currentUser;
        }

        List<Task> tasks;
        if (Objects.equals(user.getRole().getName(), "employer")) {
            tasks = user.getOffers();
        } else {
            tasks = user.getTasks();
        }

        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("tasks", tasks);
        return "profile";
    }
}
