package org.freelance.services;

import org.freelance.DAOs.TaskDao;
import org.freelance.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends AbstractService<Task> {
    @Autowired
    protected TaskDao DAO;
}
