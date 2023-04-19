package org.freelance.configure;

import org.freelance.services.UserService;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    private UserService userService = new UserService();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/authentication/**").anonymous()
                .antMatchers("/profile").hasAnyRole()
                .antMatchers("tasks/create_task").hasRole("ROLE_EMPLOYEE")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) {

    }


}