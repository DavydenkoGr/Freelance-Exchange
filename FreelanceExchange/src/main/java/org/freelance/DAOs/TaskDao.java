package org.freelance.DAOs;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.freelance.models.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TaskDao implements AbstractHibernateDao<Task> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Task find(long id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Task> findByEmployerId(long id) {
        return (List<Task>) sessionFactory.getCurrentSession().createQuery(
                "SELECT * FROM tasks WHERE employer_id = " + id
        ).list();
    }

    @SuppressWarnings("unchecked")
    public List<Task> findByEmployeeId(long id) {
        return (List<Task>) sessionFactory.getCurrentSession().createQuery(
                "SELECT * FROM tasks WHERE employee_id = " + id
        ).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> findAll() {
        return (List<Task>) sessionFactory.getCurrentSession().createQuery("SELECT * FROM tasks").list();
    }

    @Override
    public Task create(Task entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        return entity;
    }

    @Override
    public void update(Task entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    @Override
    public void delete(Task entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Task entity = find(id);
        delete(entity);
    }
}
