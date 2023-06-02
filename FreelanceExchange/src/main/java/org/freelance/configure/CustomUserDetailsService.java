package org.freelance.configure;

import org.freelance.models.Role;
import org.freelance.models.User;
import org.freelance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Service which provides user authentication
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * User loader
     * Find user with specified username in database and issue rights
     * @param username the username identifying the user whose data is required.
     * @return generated User instance
     * @throws UsernameNotFoundException if it cannot find user
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.find(username);

        if (user == null) {
            throw new UsernameNotFoundException("Cannot find user by name " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                mapRolesToAuthorities(Arrays.asList(user.getRole())));
    }

    /**
     * Generate roles list for user
     * @param roles user roles
     * @return roles list
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(
                        role.getName().toUpperCase()
                ))
                .collect(Collectors.toList());
    }
}
