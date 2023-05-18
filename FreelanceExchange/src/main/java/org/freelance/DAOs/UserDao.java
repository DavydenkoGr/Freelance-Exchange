package org.freelance.DAOs;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.freelance.models.Role;
import org.freelance.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserDao implements AbstractHibernateDao<User> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User find(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User response = session.get(User.class, id);
        transaction.commit();
        return response;
    }

    public User find(String login) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User response = session.createQuery(
                "SELECT u FROM User u WHERE u.login = '" + login + "'", User.class
        ).getSingleResult();
        transaction.commit();
        return response;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> response = session.createQuery("SELECT u FROM User u", User.class).getResultList();
        transaction.commit();
        return response;
    }

//    public List<User> findByRole(String role) {
//        Session session = sessionFactory.getCurrentSession();
//        Transaction transaction = session.beginTransaction();
//
//        String query = "SELECT u FROM User u WHERE u.role_id = (SELECT r.id FROM Role r WHERE r.name = '" +
//                role.toLowerCase() +
//                "')";
//
//        List<User> response = session.createQuery(query, User.class).getResultList();
//        transaction.commit();
//        return response;
//    }

    @Override
    public User create(User entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        return entity;
    }

    @Override
    public void update(User entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    @Override
    public void delete(User entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    @Override
    public void delete(long id) {
        User entity = find(id);
        delete(entity);
    }
}
