package org.freelance;

import org.freelance.models.Role;
import org.freelance.services.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main class which runs site
 */
@SpringBootApplication
public class Application {
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

    /**
     * Default role creation method.
     * Create "employer" and "employee" roles
     *
     * @param roleService default RoleService
     */
    private static void createRoles(RoleService roleService) {
        Role employerRole = new Role();
        employerRole.setName("employer");
        employerRole.setId(1);

        Role employeeRole = new Role();
        employeeRole.setName("employee");
        employeeRole.setId(2);

        roleService.create(employerRole);
        roleService.create(employeeRole);
    }
}
