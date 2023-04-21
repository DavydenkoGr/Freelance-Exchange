package org.freelance.services;

import org.freelance.DAOs.UserDao;
import org.freelance.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    protected UserDao DAO;

    public User find(String login) {
        return DAO.find(login);
    }

    public List<User> findByRole(String role) {
        return DAO.findByRole(role);
    }
}
