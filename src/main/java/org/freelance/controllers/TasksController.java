package org.freelance.controllers;

import jakarta.validation.Valid;
import org.freelance.forms.TaskForm;
import org.freelance.models.Task;
import org.freelance.models.User;
import org.freelance.schedulers.TasksScheduler;
import org.freelance.services.TaskService;
import org.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Calendar;

/**
 * Tasks controller
 */
@Controller
@RequestMapping("tasks")
public class TasksController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    /**
     * Task page handler
     * Display all task information
     * @param id requested task id
     * @param model transfers data to templates
     */
    @GetMapping("task")
    public String task(@RequestParam("id") String id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        Task task = taskService.find(Integer.parseInt(id));

        Integer daysBeforeExpiration = TasksScheduler.getDaysRemaining(task);

        model.addAttribute("user", user);
        model.addAttribute("task", task);
        model.addAttribute("daysBeforeExpiration", daysBeforeExpiration);
        return "task";
    }

    /**
     * Create task form for employers
     * Call /tasks/save page when user completes form
     * @param model transfers data to templates
     */
    @GetMapping("create-task")
    public String createTask(Model model) {
        model.addAttribute("form", new TaskForm());
        return "create-task";
    }

    /**
     * Create task form handler, check if task valid and save to database
     * @param form completed task form
     * @param result processing report
     */
    @PostMapping("save")
    public String saveTask(@Valid @ModelAttribute("form") TaskForm form, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        if (result.hasErrors()) {
            return "create-task";
        }

        Task task = new Task();

        task.setName(form.getName());
        task.setEmployer(user);
        task.setPrice(form.getPrice());
        task.setDescription(form.getDescription());

        taskService.create(task);

        return "redirect:/profile";
    }

    /**
     * Request page for employers to delete their tasks with specified id
     * @param id task id
     */
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

    /**
     * Request page for employees, assign task to employee
     * @param id task id
     */
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

    /**
     * Request page for employees, mark task as completed
     * @param id task id
     */
    @GetMapping("complete")
    public String completeTask(@RequestParam("id") String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        Task task = taskService.find(Integer.parseInt(id));

        if (task.getEmployee() == null || user.getId() != task.getEmployee().getId()) {
            return "redirect:/freelance";
        }

        task.setCompleteDate(Calendar.getInstance().getTime());
        taskService.update(task);

        return "redirect:/tasks/task?id=" + id;
    }
}
