package org.freelance.DAO;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.freelance.models.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Task dao implementation
 */
@Repository
@Transactional
public class TaskDao implements AbstractHibernateDao<Task> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Task find(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Task response = session.get(Task.class, id);
        transaction.commit();
        return response;
    }

    @Override
    public List<Task> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Task> response = session.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        transaction.commit();
        return response;
    }

    /**
     * Find all task in database with specified employer id
     * @param id employer id
     * @return list of founded tasks
     */
    public List<Task> findByEmployerId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Task> response = session.createQuery(
                "SELECT t FROM Task t WHERE t.employer.id = " + id, Task.class
        ).getResultList();
        transaction.commit();
        return response;
    }

    /**
     * Find all task in database with specified employee id
     * @param id employee id
     * @return list of founded tasks
     */
    public List<Task> findByEmployeeId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Task> response = session.createQuery(
                "SELECT t FROM Task t WHERE t.employee != null and t.employee.id = " + id, Task.class
        ).getResultList();
        transaction.commit();
        return response;
    }

    /**
     * Find all free task
     * @return list of founded free tasks
     */
    public List<Task> findFree() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Task> response = session.createQuery(
                "SELECT t FROM Task t WHERE t.employee = null", Task.class
        ).getResultList();
        transaction.commit();
        return response;
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
