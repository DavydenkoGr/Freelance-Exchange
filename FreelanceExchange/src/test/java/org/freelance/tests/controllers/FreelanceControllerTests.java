package org.freelance.tests.controllers;

import org.apache.tomcat.util.digester.ArrayStack;
import org.freelance.controllers.FreelanceController;
import org.freelance.models.Task;
import org.freelance.services.TaskService;
import org.freelance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FreelanceControllerTests {
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
        when(taskService.findFree()).thenReturn(new ArrayStack<Task>());

        when(userService.find(anyString())).thenReturn(null);
        controller.freelance(model, "test");
    }
}