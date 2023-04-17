package org.freelance;

import org.freelance.services.EmployeeService;
import org.freelance.services.EmployerService;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private EmployerService employerService = new EmployerService();
    private EmployeeService employeeService = new EmployeeService();

}
