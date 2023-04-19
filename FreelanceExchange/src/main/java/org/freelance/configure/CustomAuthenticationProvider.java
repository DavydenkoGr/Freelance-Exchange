//package org.freelance.configure;
//
//import org.freelance.models.User;
//import org.freelance.services.UserService;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    private UserService userService = new UserService();
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getDetails();
//        String password = authentication.getCredentials().toString();
//
//        User user = userService.find()
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
