package org.freelance.controllers;

import jakarta.validation.Valid;
import org.freelance.forms.ProfileEditForm;
import org.freelance.models.Task;
import org.freelance.models.User;
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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Objects;

/**
 * User profile controller
 */
@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    /**
     * User profile handler
     * Show list of user tasks and main user information
     * @param model transfers data to templates
     */
    @GetMapping("profile")
    public String profile(@RequestParam(name = "id", required = false) String id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.find(authentication.getName());

        User user;
        if (id != null) {
            user = userService.find(Integer.parseInt(id));
        } else {
            user = currentUser;
        }

        List<Task> tasks;
        if (Objects.equals(user.getRole().getName(), "employer")) {
            tasks = user.getOffers();
        } else {
            tasks = user.getTasks();
        }

        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("tasks", tasks);
        return "profile";
    }

    /**
     * User editor page handler
     * Contain edit form for user
     * Call /profile/edit/save page when user completes form
     * @param model transfers data to templates
     */
    @GetMapping("profile/edit")
    public String edit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.find(authentication.getName());

        ProfileEditForm form = new ProfileEditForm();

        form.setLogin(user.getLogin());
        form.setName(user.getName());
        form.setSurname(user.getSurname());
        form.setAge(user.getAge());
        form.setAddress(user.getAddress());
        form.setOrganization(user.getOrganization());
        form.setInformation(user.getInformation());
        form.setResume(user.getResume());
        form.setRoleName(user.getRole().getName());

        model.addAttribute("form", form);
        return "profile-edit";
    }

    /**
     * Profile edit form handler, check if form valid and update user
     * @param form valid edit form
     * @param result processing report
     */
    @PostMapping("profile/edit/save")
    public String registrationSave(@Valid @ModelAttribute("form") ProfileEditForm form, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.find(authentication.getName());

        User foundedUser = userService.find(form.getLogin());

        if (foundedUser != null && foundedUser.getId() != currentUser.getId()) {
            result.rejectValue("login", "1", "Login exists");
        }

        if (form.getName().equals("anonymousUser")) {
            result.rejectValue("login", "2", "Forbidden login");
        }

        if (result.hasErrors()) {
            return "redirect:/profile";
        }

        currentUser.setName(form.getName());
        currentUser.setSurname(form.getSurname());
        currentUser.setAge(form.getAge());
        currentUser.setAddress(form.getAddress());
        currentUser.setOrganization(form.getOrganization());
        currentUser.setInformation(form.getInformation());
        currentUser.setResume(form.getResume());

        if (!Objects.equals(currentUser.getLogin(), form.getLogin())) {
            currentUser.setLogin(form.getLogin());
            userService.update(currentUser);
            return "redirect:/logout";
        }

        userService.update(currentUser);

        return "redirect:/profile";
    }
}
