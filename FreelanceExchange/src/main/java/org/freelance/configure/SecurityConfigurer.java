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

/**
 * Security configurer, provide user authentication
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    /**
     * General password encoder
     * @return password encoder instance
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Filter for http requests, control access to different site pages
     * @param http request address
     * @return built http
     * @throws Exception if there is a problem with processing
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/authentication/**", "/freelance", "/", "/index", "/tasks/task", "/user").permitAll()
                                .requestMatchers("/profile/**").authenticated()
                                .requestMatchers("/tasks/create-task", "/tasks/save", "/tasks/delete").hasAuthority("EMPLOYER")
                                .requestMatchers("/tasks/accept", "/tasks/complete").hasAuthority("EMPLOYEE")
                ).formLogin(
                        form -> form
                                .loginPage("/authentication/login")
                                .loginProcessingUrl("/authentication/login")
                                .defaultSuccessUrl("/profile", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    /**
     * Charge authentication to CustomUserDetailsService and password encoder
     * @param auth authentication object
     * @throws Exception if there is problems with authentication
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
