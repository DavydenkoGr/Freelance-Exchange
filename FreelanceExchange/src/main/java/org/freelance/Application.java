package org.freelance;

import org.freelance.models.Role;
import org.freelance.models.User;
import org.freelance.services.RoleService;
import org.freelance.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        RoleService roleService = context.getBean(RoleService.class);
        UserService userService = context.getBean(UserService.class);

        Role role = new Role();
        role.setName("role");
        roleService.create(role);

        User user = new User();

        user.setLogin("login");
        user.setPassword("111");
        user.setName("name");
        user.setSurname("surname");
        user.setRole(role);

        userService.create(user);

        userService.delete(1);
    }
}
