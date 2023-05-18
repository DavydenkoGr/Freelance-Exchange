//package org.freelance.controllers;
//
//import org.freelance.models.User;
//import org.freelance.services.RoleService;
//import org.freelance.services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("authentication")
//public class AuthenticationController {
//    @Autowired
//    UserService userService;
//    @Autowired
//    RoleService roleService;
//
//    @GetMapping("employer/login")
//    public String employerLogin(Model model) {
//        return "employer-login-form";
//    }
//
//    @GetMapping("employee/login")
//    public String employeeLogin(Model model) {
//        return "employer-login-form";
//    }
//
//    @GetMapping("employer/registration")
//    public String employerRegistration(Model model) {
//        return "employer-registration-form";
//    }
//
//    @GetMapping("employee/registration")
//    public String employeeRegistration(Model model) {
//        User user = new User();
//        user.setRole(roleService.find(1));
//        model.addAttribute("user", user);
//        return "employee-registration-form";
//    }
//}
