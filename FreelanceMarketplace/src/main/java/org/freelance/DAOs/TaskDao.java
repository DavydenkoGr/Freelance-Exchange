package org.freelance.DAOs;

import org.freelance.models.Task;

import java.util.List;

public class TaskDao extends AbstractHibernateDao<Task> {
    @Override
    public Task find(long id) {
        return getCurrentSession().get(Task.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> findAll() {
        return (List<Task>) getCurrentSession().createQuery("FROM tasks").list();
    }
}
