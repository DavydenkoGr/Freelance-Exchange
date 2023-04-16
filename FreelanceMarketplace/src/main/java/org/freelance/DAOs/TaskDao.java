package org.freelance.DAOs;

import org.freelance.models.Task;

import java.util.List;

public class TaskDao extends AbstractHibernateDao<Task> {
    @Override
    public Task find(long id) {
        return getCurrentSession().get(Task.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Task> findByEmployerId(long id) {
        return (List<Task>) getCurrentSession().createQuery(
                "SELECT * FROM tasks WHERE employer_id = " + id
        ).list();
    }

    @SuppressWarnings("unchecked")
    public List<Task> findByEmployeeId(long id) {
        return (List<Task>) getCurrentSession().createQuery(
                "SELECT * FROM tasks WHERE employee_id = " + id
        ).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> findAll() {
        return (List<Task>) getCurrentSession().createQuery("SELECT * FROM tasks").list();
    }
}
