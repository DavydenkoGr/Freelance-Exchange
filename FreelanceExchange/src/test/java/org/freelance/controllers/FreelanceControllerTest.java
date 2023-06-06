package org.freelance.controllers;

import jdk.dynalink.beans.StaticClass;
import org.apache.tomcat.util.digester.ArrayStack;
import org.freelance.forms.RegistrationForm;
import org.freelance.models.Task;
import org.freelance.models.User;
import org.freelance.services.RoleService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FreelanceControllerTest {
    @Mock(name="userService")
    private UserService userService;
    @Mock(name="taskService")
    private TaskService taskService;
    @Mock
    private Model model;

    @InjectMocks
    private FreelanceController controller;


    @Test
    void freelanceTest() {
        BindingResult mockResult = mock(BindingResult.class);

        Authentication mockAuth = mock(Authentication.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        try (MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic = mockStatic(SecurityContextHolder.class)) {
            securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
            when(mockSecurityContext.getAuthentication()).thenReturn(mockAuth);
            when(mockAuth.getName()).thenReturn("test");
            when(taskService.findFree()).thenReturn(new ArrayStack<Task>());

            when(userService.find(anyString())).thenReturn(null);
            controller.freelance(model, "test");
        }
    }
}
