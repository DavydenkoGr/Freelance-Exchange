package org.freelance.services;

import org.freelance.DAO.UserDao;
import org.freelance.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * User service which provides data transfer between user dao and other classes
 * Send requests to UserDao
 */
@Service
public class UserService implements AbstractService<User> {
    @Autowired
    protected UserDao DAO;

    /**
     * Send find request
     * @param login user login
     * @return found user or null if not exist
     */
    public User find(String login) {
        return DAO.find(login);
    }

    /**
     * Send find request
     * @param id user id
     * @return found user or null if not exist
     */
    @Override
    public User find(long id) {
        return DAO.find(id);
    }

    /**
     * Send request to find all users
     * @return found users list
     */
    @Override
    public List<User> findAll() {
        return DAO.findAll();
    }

    /**
     * Send save request
     * @param entity user instance
     * @return saved user
     */
    @Override
    public User create(User entity) {
        return DAO.create(entity);
    }

    /**
     * Send save or update request
     * @param entity user instance
     */
    @Override
    public void update(User entity) {
        DAO.update(entity);
    }

    /**
     * Send delete request
     * @param entity user instance
     */
    @Override
    public void delete(User entity) {
        DAO.delete(entity);
    }

    /**
     * Send delete by id request
     * @param id user id
     */
    @Override
    public void delete(long id) {
        DAO.delete(id);
    }
}
