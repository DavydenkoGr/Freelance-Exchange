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

/**
 * Main page controller
 */
@Controller
public class FreelanceController {
    @Autowired
    private TaskService taskService;

    /**
     * Menu handler, show list of active tasks
     * Support substring search
     * @param model transfers data to templates
     * @param request search request, null if absence
     */
    @GetMapping(path = {"/freelance", "/", "/index"})
    public String freelance(Model model, String request) {
        List<Task> tasks = taskService.findFree();

        if (request != null) {
            tasks = tasks.stream()
                    .filter(task -> task.getName().toLowerCase().contains(request.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("tasks", tasks);
        return "freelance";
    }
}
