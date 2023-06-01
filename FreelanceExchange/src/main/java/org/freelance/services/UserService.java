package org.freelance.services;

import org.freelance.DAO.UserDao;
import org.freelance.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements AbstractService<User> {
    @Autowired
    protected UserDao DAO;

    public User find(String login) {
        return DAO.find(login);
    }

    @Override
    public User find(long id) {
        return DAO.find(id);
    }

    @Override
    public List<User> findAll() {
        return DAO.findAll();
    }

    @Override
    public User create(User entity) {
        return DAO.create(entity);
    }

    @Override
    public void update(User entity) {
        DAO.update(entity);
    }

    @Override
    public void delete(User entity) {
        DAO.delete(entity);
    }

    @Override
    public void delete(long id) {
        DAO.delete(id);
    }
}
