package org.freelance.services;

import org.freelance.DAOs.AbstractHibernateDao;
import org.freelance.DAOs.UserDao;
import org.freelance.models.User;

public class UserService extends AbstractService<User> {
    protected UserDao DAO = new UserDao();

    public User find(String login) {
        return DAO.find(login);
    }
}
