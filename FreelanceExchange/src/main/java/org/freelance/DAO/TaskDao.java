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
 * Dao object which organize work with database tasks table using SessionFactory object
 */
@Repository
@Transactional
public class TaskDao implements AbstractHibernateDao<Task> {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find task by id in database
     * @param id task id
     * @return found task or null if not exist
     */
    @Override
    public Task find(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Task response = session.get(Task.class, id);
        transaction.commit();
        return response;
    }

    /**
     * Find all tasks in database table
     * @return found tasks list
     */
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

    /**
     * Find all completed task
     * @return list of founded completed tasks
     */
    public List<Task> findCompleted() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Task> response = session.createQuery(
                "SELECT t FROM Task t WHERE t.completeDate != null", Task.class
        ).getResultList();
        transaction.commit();
        return response;
    }

    /**
     * Save task instance to database
     * @param entity task instance
     * @return task
     */
    @Override
    public Task create(Task entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        return entity;
    }

    /**
     * Save or update task instance
     * @param entity task instance
     */
    @Override
    public void update(Task entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    /**
     * Delete task instance from database
     * @param entity task instance
     */
    @Override
    public void delete(Task entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    /**
     * Delete task instance from database
     * @param id task id
     */
    @Override
    public void delete(long id) {
        Task entity = find(id);
        delete(entity);
    }
}
