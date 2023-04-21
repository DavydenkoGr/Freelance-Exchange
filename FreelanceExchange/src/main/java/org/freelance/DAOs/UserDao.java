package org.freelance.DAOs;

import org.freelance.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    @SuppressWarnings("unchecked")
    public List<User> findByRole(String role) {
        StringBuilder query = new StringBuilder();

        query
                .append("SELECT * FROM users WHERE role_id = (SELECT id FROM roles WHERE name = '")
                .append(role.toLowerCase())
                .append("')");

        return (List<User>) getCurrentSession().createQuery(query.toString()).list();
    }
}
