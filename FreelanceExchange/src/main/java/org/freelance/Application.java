package org.freelance;

import org.freelance.models.Role;
import org.freelance.models.User;
import org.freelance.services.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

/**
 * Main class which runs site
 */
@SpringBootApplication
public class Application {

    /**
     * Default role creation method.
     * Create "employer" and "employee" roles
     *
     * @param roleService default RoleService
     */
    private static void createRoles(RoleService roleService) {
        Role employer = new Role(1, "employer", new ArrayList<User>());
        Role employee = new Role(2, "employee", new ArrayList<User>());

        roleService.create(employer);
        roleService.create(employee);
    }

    /**
     * Main method
     * Run SpringBootApplication
     * @param args command line args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        RoleService roleService = context.getBean(RoleService.class);

        if (roleService.findByName("employer") == null || roleService.findByName("employee") == null) {
            createRoles(roleService);
        }
    }
}
