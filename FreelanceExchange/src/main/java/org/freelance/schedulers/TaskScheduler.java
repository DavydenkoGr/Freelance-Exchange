package org.freelance.schedulers;

import org.freelance.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskScheduler {
    @Autowired
    private TaskService taskService;

    @Scheduled(fixedDelayString = "PT030S")
    private void checkExpiration() {

    }
}
