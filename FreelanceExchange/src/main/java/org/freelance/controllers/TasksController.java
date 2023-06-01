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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("tasks")
public class TasksController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @GetMapping("task")
    public String task(@RequestParam("id") String id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        Task task = taskService.find(Integer.parseInt(id));

        model.addAttribute("user", user);
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("create-task")
    public String createTask(Model model) {
        Task task = new Task();

        model.addAttribute("task", task);
        return "create-task";
    }

    @PostMapping("save")
    public String saveTask(@ModelAttribute("task") Task task, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        if (result.hasErrors()) {
            return "create-task";
        }

        task.setEmployer(user);
        taskService.create(task);

        return "redirect:/profile";
    }

    @GetMapping("delete")
    public String deleteTask(@RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        Task task = taskService.find(Integer.parseInt(id));

        if (user.getId() != task.getEmployer().getId()) {
            return "redirect:/freelance";
        }

        taskService.delete(task);

        return "redirect:/profile";
    }

    @GetMapping("accept")
    public String acceptTask(@RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        Task task = taskService.find(Integer.parseInt(id));

        if (task.getEmployee() != null) {
            return "redirect:/freelance";
        }

        task.setEmployee(user);
        taskService.update(task);

        return "redirect:/tasks/task?id=" + id;
    }

    @GetMapping("complete")
    public String completeTask(@RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        Task task = taskService.find(Integer.parseInt(id));

        if (task.getEmployee() == null || user.getId() != task.getEmployee().getId()) {
            return "redirect:/freelance";
        }

        task.setCompleted(true);
        taskService.update(task);

        return "redirect:/tasks/task?id=" + id;
    }
}
