package org.freelance.services;

import org.freelance.DAO.TaskDao;
import org.freelance.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Task service which provides data transfer between task dao and other classes
 * Send requests to TaskDao
 */
@Service
public class TaskService implements AbstractService<Task> {
    @Autowired
    protected TaskDao DAO;

    /**
     * Send find request
     * @param id task id
     * @return found task or null if not exist
     */
    @Override
    public Task find(long id) {
        return DAO.find(id);
    }

    /**
     * Send request to find all tasks
     * @return found tasks list
     */
    @Override
    public List<Task> findAll() {
        return DAO.findAll();
    }

    /**
     * Send request to find all tasks with specified employer id
     * @param id employer id
     * @return found tasks list
     */
    public List<Task> findByEmployerId(long id) {
        return DAO.findByEmployerId(id);
    }

    /**
     * Send request to find all tasks with specified employee id
     * @param id employee id
     * @return found tasks list
     */
    public List<Task> findByEmployeeId(long id) {
        return DAO.findByEmployeeId(id);
    }

    /**
     * Send request to find all free tasks
     * @return found tasks list
     */
    public List<Task> findFree() {
        return DAO.findFree();
    }

    /**
     * Send request to find all completed tasks
     * @return found tasks list
     */
    public List<Task> findCompleted() {
        return DAO.findCompleted();
    }

    /**
     * Send save request
     * @param entity task instance
     * @return saved task
     */
    @Override
    public Task create(Task entity) {
        return DAO.create(entity);
    }

    /**
     * Send save or update request
     * @param entity task instance
     */
    @Override
    public void update(Task entity) {
        DAO.update(entity);
    }

    /**
     * Send delete request
     * @param entity task instance
     */
    @Override
    public void delete(Task entity) {
        DAO.delete(entity);
    }

    /**
     * Send delete by id request
     * @param id task id
     */
    @Override
    public void delete(long id) {
        DAO.delete(id);
    }
}
