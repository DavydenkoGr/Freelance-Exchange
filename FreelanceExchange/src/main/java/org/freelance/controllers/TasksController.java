//package org.freelance.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("tasks")
//public class TasksController {
//    @GetMapping("task")
//    public String task(Model model, @RequestParam("id") String id) {
//        model.addAttribute("name", id);
//        return "test";
//    }
//    @GetMapping("create_task")
//    public String createTask(Model model, @RequestParam("id") String id) {
//        model.addAttribute("name", id);
//        return "create-task";
//    }
//}
