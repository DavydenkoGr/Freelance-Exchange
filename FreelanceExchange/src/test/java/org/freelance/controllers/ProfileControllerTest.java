package org.freelance.controllers;

import org.apache.tomcat.util.digester.ArrayStack;
import org.freelance.models.Role;
import org.freelance.models.Task;
import org.freelance.models.User;
import org.freelance.services.TaskService;
import org.freelance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProfileControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private TaskService taskService;
    @Mock
    private Model model;

    @InjectMocks
    private ProfileController controller;


    @Test
    void profileTest() {
        /*Authentication mockAuth = mock(Authentication.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);

        try (MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic = mockStatic(SecurityContextHolder.class)) {
            securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
            when(mockSecurityContext.getAuthentication()).thenReturn(mockAuth);

            User mockUser = mock(User.class);
            Role employerRole = mock(Role.class);

            when(employerRole.getName()).thenReturn("employer");
            when(taskService.findByEmployerId(anyInt())).thenReturn(new ArrayList<Task>());

            when(userService.find(anyString())).thenReturn(mockUser);
            controller.profile(model);
        }*/
    }
}
