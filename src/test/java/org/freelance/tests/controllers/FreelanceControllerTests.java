package org.freelance.tests.controllers;

import org.freelance.controllers.FreelanceController;
import org.freelance.services.TaskService;
import org.freelance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FreelanceControllerTests {
    @Mock
    private UserService userService;
    @Mock
    private TaskService taskService;
    @Mock
    private Model model;
    @InjectMocks
    private FreelanceController controller;

    @Test
    public void freelanceTest() {
        when(taskService.findFree()).thenReturn(new ArrayList<>());
        when(userService.find(anyString())).thenReturn(null);

        assertEquals(controller.freelance(model, "test"), "freelance");
    }
}