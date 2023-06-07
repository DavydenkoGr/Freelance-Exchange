package org.freelance.tests.controllers;

import org.freelance.controllers.AuthenticationController;
import org.freelance.forms.RegistrationForm;
import org.freelance.models.User;
import org.freelance.services.RoleService;
import org.freelance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticationControllerTest {
    @Mock(name="userService")
    private UserService userService;
    @Mock(name="roleService")
    private RoleService roleService;
    @Mock(name="passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Mock
    private Model model;

    @InjectMocks
    private AuthenticationController controller;

    @Test
    void loginTest() {
        assertEquals(controller.login(), "login-form");
    }

    @Test
    void employerRegistrationFormTest() {
        assertEquals(controller.employerRegistration(model), "employer-registration-form");
    }

    @Test
    void employeeRegistrationFormTest() {
        assertEquals(controller.employeeRegistration(model), "employee-registration-form");
    }

    @Test
    void existentUserRegistrationTest() {
        RegistrationForm mockForm = new RegistrationForm();
        mockForm.setLogin("test");
        mockForm.setName("test");

        BindingResult mockResult = mock(BindingResult.class);

        when(userService.find(anyString())).thenReturn(new User());
        controller.registrationSave(mockForm, mockResult);

        verify(mockResult).rejectValue("login", "1", "Login exists");
    }

    @Test
    void anonymousUserTest() {
        RegistrationForm mockForm = new RegistrationForm();
        mockForm.setLogin("test");
        mockForm.setName("anonymousUser");

        BindingResult mockResult = mock(BindingResult.class);

        when(userService.find(anyString())).thenReturn(null);
        controller.registrationSave(mockForm, mockResult);

        verify(mockResult).rejectValue("login", "2", "Forbidden login");
    }

    @Test
    void errorsTest() {
        RegistrationForm mockForm = new RegistrationForm();
        mockForm.setLogin("test");
        mockForm.setName("test");

        BindingResult mockResult = mock(BindingResult.class);

        when(mockResult.hasErrors()).thenReturn(true);
        when(userService.find(anyString())).thenReturn(null);
        assertEquals(controller.registrationSave(mockForm, mockResult), "redirect:/authentication/employer/registration");
    }
}