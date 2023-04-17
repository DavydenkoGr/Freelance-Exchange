package services;

import org.freelance.DAOs.AbstractHibernateDao;
import org.freelance.DAOs.TaskDao;
import org.freelance.models.Task;

public abstract class TaskService<T> {
    protected AbstractHibernateDao<Task> DAO = new TaskDao();
}
