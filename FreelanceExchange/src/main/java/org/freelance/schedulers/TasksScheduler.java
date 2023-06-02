package org.freelance.schedulers;

import org.freelance.models.Task;
import org.freelance.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;

/**
 * Scheduler responsible for tasks
 */
@Service
public class TasksScheduler {
    private final static int TWO_WEEKS_MS = 1209600000;
    private final static int DAY_MS = 86400000;
    @Autowired
    private TaskService taskService;

    /**
     * Delete tasks completed more than two weeks ago
     */
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

    /**
     * Calculate days remaining before task deleting (two weeks)
     * @param task completed task
     * @return days before expiration
     */
    public static Integer getDaysRemaining(Task task) {
        if (task.getCompleteDate() == null) {
            return null;
        }

        long currentTime = Calendar.getInstance().getTime().getTime();
        return Math.max(0, 14 - (int) ((currentTime - task.getCompleteDate().getTime()) / DAY_MS));
    }
}
