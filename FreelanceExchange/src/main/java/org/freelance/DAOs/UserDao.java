package org.freelance.DAOs;

import org.freelance.models.User;

import java.util.List;

public class UserDao extends AbstractHibernateDao<User> {
    @Override
    public User find(long id) {
        return getCurrentSession().get(User.class, id);
    }

    public User find(String login) {
        return (User) getCurrentSession().createQuery(
                "SELECT 1 FROM users WHERE login = login"
        ).getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) getCurrentSession().createQuery("FROM users").list();
    }
}
