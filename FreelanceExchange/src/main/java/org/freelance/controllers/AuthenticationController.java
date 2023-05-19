package org.freelance.controllers;

import jakarta.persistence.NoResultException;
import org.freelance.models.User;
import org.freelance.services.RoleService;
import org.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authentication")
public class AuthenticationController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("employer/login")
    public String employerLogin(Model model) {
        return "employer-login-form";
    }

    @GetMapping("employee/login")
    public String employeeLogin(Model model) {
        return "employer-login-form";
    }

    @GetMapping("employer/registration")
    public String employerRegistration(Model model) {
        return "employer-registration-form";
    }

    @GetMapping("employee/registration")
    public String employeeRegistration(Model model) {
        User user = new User();
        user.setRole(roleService.find(2));
        model.addAttribute("user", user);
        return "employee-registration-form";
    }

    @PostMapping("employee/registration/save")
    public String employeeRegistrationSave(@ModelAttribute("user") User user, BindingResult result, Model model) {
        User existingUser = userService.find(user.getLogin());

        if (existingUser != null) {
            result.rejectValue("login", "1", "Login exists");
        }

        if (result.hasErrors()) {
            model.addAttribute(user);
            return "authentication/employee/registration";
        }

        user.setRole(roleService.find(2));
        userService.create(user);

        return "redirect:authentication/employee/login";
    }
}
