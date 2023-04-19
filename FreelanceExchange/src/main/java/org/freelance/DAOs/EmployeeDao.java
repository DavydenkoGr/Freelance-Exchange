package org.freelance.DAOs;

import org.freelance.models.User;

import java.util.List;

public class EmployeeDao extends AbstractHibernateDao<User> {
    @Override
    public User find(long id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return (List<User>) getCurrentSession().createQuery("FROM users").list();
    }
}
