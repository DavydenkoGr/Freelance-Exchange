package org.freelance.configure;

import org.freelance.models.User;
import org.freelance.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService = new UserService();

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.find(username);
        String role = user.getRole().getName();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (role.equals("employer")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYER"));
        } else if (role.equals("employee")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                grantedAuthorities);
    }
}
