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
import java.util.stream.Collectors;

@Controller
public class FreelanceController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @GetMapping("freelance")
    public String freelance(Model model, String request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());
        System.out.println(request);

        List<Task> tasks = taskService.findFree();

        if (request != null) {
            tasks = tasks.stream()
                    .filter(task -> task.getName().toLowerCase().contains(request.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("user", user);
        model.addAttribute("tasks", tasks);
        return "freelance";
    }
}
