package org.freelance.services;

import org.freelance.DAOs.AbstractHibernateDao;
import org.freelance.DAOs.TaskDao;
import org.freelance.models.Task;

public class TaskService extends AbstractService<Task> {
    protected AbstractHibernateDao<Task> DAO = new TaskDao();
}
