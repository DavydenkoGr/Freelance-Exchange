package org.freelance.controllers;

import jakarta.validation.Valid;
import org.freelance.forms.RegistrationForm;
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

/**
 * Login and register controller
 */
@Controller
@RequestMapping("authentication")
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Login page handler
     */
    @GetMapping("login")
    public String login() {
        return "login-form";
    }

    /**
     * Employer registration page handler
     * Contain registration form for user
     * Call /authentication/registration/save page when user completes form
     * @param model transfers data to templates
     */
    @GetMapping("employer/registration")
    public String employerRegistration(Model model) {
        RegistrationForm form = new RegistrationForm();
        form.setRoleId(1);

        model.addAttribute("form", form);
        return "employer-registration-form";
    }

    /**
     * Employee registration page handler
     * Contain registration form for user
     * Call /authentication/registration/save page when user completes form
     * @param model transfers data to templates
     */
    @GetMapping("employee/registration")
    public String employeeRegistration(Model model) {
        RegistrationForm form = new RegistrationForm();
        form.setRoleId(2);

        model.addAttribute("form", form);
        return "employee-registration-form";
    }

    /**
     * Registration form handler, check if form valid and create new user
     * @param form valid registration form
     * @param result processing report
     */
    @PostMapping("registration/save")
    public String registrationSave(@Valid @ModelAttribute("form") RegistrationForm form, BindingResult result) {
        User existingUser = userService.find(form.getLogin());

        if (existingUser != null) {
            result.rejectValue("login", "1", "Login exists");
        }

        if (form.getName().equals("anonymousUser")) {
            result.rejectValue("login", "2", "Forbidden login");
        }

        if (result.hasErrors()) {
            return "redirect:/authentication/employer/registration";
        }

        User newUser = new User();

        newUser.setRole(roleService.find(form.getRoleId()));
        newUser.setLogin(form.getLogin());
        newUser.setPassword(passwordEncoder.encode(form.getPassword()));
        newUser.setName(form.getName());
        newUser.setSurname(form.getSurname());
        newUser.setAge(form.getAge());
        newUser.setResume(form.getResume());
        newUser.setOrganization(form.getOrganization());

        userService.create(newUser);

        return "redirect:/authentication/login";
    }
}
