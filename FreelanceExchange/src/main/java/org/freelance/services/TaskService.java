package org.freelance.services;

import org.freelance.DAO.TaskDao;
import org.freelance.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService implements AbstractService<Task> {
    @Autowired
    protected TaskDao DAO;

    @Override
    public Task find(long id) {
        return DAO.find(id);
    }

    @Override
    public List<Task> findAll() {
        return DAO.findAll();
    }

    public List<Task> findByEmployerId(long id) {
        return DAO.findByEmployerId(id);
    }

    public List<Task> findByEmployeeId(long id) {
        return DAO.findByEmployeeId(id);
    }

    public List<Task> findFree() {
        return DAO.findFree();
    }

    @Override
    public Task create(Task entity) {
        return DAO.create(entity);
    }

    @Override
    public void update(Task entity) {
        DAO.update(entity);
    }

    @Override
    public void delete(Task entity) {
        DAO.delete(entity);
    }

    @Override
    public void delete(long id) {
        DAO.delete(id);
    }
}
