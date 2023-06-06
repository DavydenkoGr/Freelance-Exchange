package org.freelance.controllers;

import org.freelance.models.Role;
import org.freelance.models.Task;
import org.freelance.models.User;
import org.freelance.services.TaskService;
import org.freelance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskControllerTest {}
