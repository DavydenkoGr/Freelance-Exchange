package org.freelance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authentication")
public class AuthenticationController {
    @GetMapping("employer/login")
    public String employerLogin(Model model) {
        return "employer-login-form";
    }

    @GetMapping("employee/login")
    public String employeeLogin(Model model) {
        return "employer-login-form";
    }

    @GetMapping("employerRegistration")
    public String employerRegistration(Model model) {
        return "employer-registration-form";
    }

    @GetMapping("employeeRegistration")
    public String employeeRegistration(Model model) {
        return "employee-registration-form";
    }
}
