package org.freelance.DAO;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.freelance.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Dao object which organize work with database users table using SessionFactory object
 */
@Repository
@Transactional
public class UserDao implements AbstractHibernateDao<User> {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find user by id in database
     * @param id user id
     * @return found user or null if not exist
     */
    @Override
    public User find(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User response = session.get(User.class, id);
        transaction.commit();
        return response;
    }

    /**
     * Find user by unique login
     * @param login user login
     * @return founded user instance
     */
    public User find(String login) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User response = session.createQuery(
                "SELECT u FROM User u WHERE u.login = ?1", User.class
        ).setParameter(1, login).getSingleResultOrNull();

        transaction.commit();
        return response;
    }

    /**
     * Find all users in database table
     * @return found users list
     */
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

    /**
     * Save user instance to database
     * @param entity user instance
     * @return user
     */
    @Override
    public User create(User entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        return entity;
    }

    /**
     * Save or update user instance
     * @param entity user instance
     */
    @Override
    public void update(User entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    /**
     * Delete user instance from database
     * @param entity user instance
     */
    @Override
    public void delete(User entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    /**
     * Delete user instance from database
     * @param id user id
     */
    @Override
    public void delete(long id) {
        User entity = find(id);
        delete(entity);
    }
}
