package org.freelance.schedulers;

import org.freelance.models.Task;
import org.freelance.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;

@Service
public class TasksScheduler {
    private final static int TWO_WEEKS_MS = 1209600000;
    @Autowired
    private TaskService taskService;

    @Scheduled(fixedDelayString = "PT01H")
    private void checkExpiration() {
        List<Task> tasks = taskService.findCompleted();
        long currentTime = Calendar.getInstance().getTime().getTime();

        for (Task task : tasks) {
            if (currentTime - task.getCompleteDate().getTime() > TWO_WEEKS_MS) {
                taskService.delete(task);
            }
        }
    }
}
