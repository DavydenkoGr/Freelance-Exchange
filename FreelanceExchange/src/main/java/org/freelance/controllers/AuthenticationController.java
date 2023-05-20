package org.freelance.controllers;

import org.freelance.models.User;
import org.freelance.services.RoleService;
import org.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("login")
    public String login(Model model) {
        return "login-form";
    }

    @GetMapping("employer/registration")
    public String employerRegistration(Model model) {
        User user = new User();
        user.setRole(roleService.find(1));
        model.addAttribute("user", user);
        return "employer-registration-form";
    }

    @GetMapping("employee/registration")
    public String employeeRegistration(Model model) {
        User user = new User();
        user.setRole(roleService.find(2));
        model.addAttribute("user", user);
        return "employee-registration-form";
    }

    @PostMapping("registration/save")
    public String employeeRegistrationSave(@ModelAttribute("user") User user, BindingResult result, Model model) {
        User existingUser = userService.find(user.getLogin());

        if (existingUser != null) {
            result.rejectValue("login", "1", "Login exists");
        }

        if (result.hasErrors()) {
            model.addAttribute(user);
            return "authentication/employee/registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);

        return "redirect:authentication/login";
    }
}
