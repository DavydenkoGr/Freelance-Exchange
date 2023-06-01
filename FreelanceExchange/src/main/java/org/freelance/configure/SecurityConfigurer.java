package org.freelance.configure;

import org.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/authentication/**", "/freelance", "/tasks/task").permitAll()
                                .requestMatchers("/profile").authenticated()
                                .requestMatchers("/tasks/create-task", "/tasks/save", "/tasks/delete").hasAuthority("EMPLOYER")
                                .requestMatchers("/tasks/accept", "/tasks/complete").hasAuthority("EMPLOYEE")
                ).formLogin(
                        form -> form
                                .loginPage("/authentication/login")
                                .loginProcessingUrl("/authentication/login")
                                .defaultSuccessUrl("/profile")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
