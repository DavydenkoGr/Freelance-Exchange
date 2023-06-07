package org.freelance.DAO;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.freelance.models.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Dao object which organize work with database roles table using SessionFactory object
 */
@Repository
@Transactional
public class RoleDao implements AbstractHibernateDao<Role> {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find role by id in database
     * @param id role id
     * @return found role or null if not exist
     */
    @Override
    public Role find(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Role response = session.get(Role.class, id);
        transaction.commit();
        return response;
    }

    /**
     * Find all roles in database table
     * @return found roles list
     */
    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Role> response = session.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        transaction.commit();
        return response;
    }

    /**
     * Find role in database by name
     * @param name role name
     * @return founded role
     */
    public Role findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Role result = session.createQuery("SELECT r FROM Role r WHERE r.name = ?1", Role.class)
                .setParameter(1, name).getSingleResultOrNull();

        transaction.commit();

        return result;
    }

    /**
     * Save role instance to database
     * @param entity role instance
     * @return role
     */
    @Override
    public Role create(Role entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        return entity;
    }

    /**
     * Save or update role instance
     * @param entity role instance
     */
    @Override
    public void update(Role entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    /**
     * Delete role instance from database
     * @param entity role instance
     */
    @Override
    public void delete(Role entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    /**
     * Delete role instance from database
     * @param id role id
     */
    @Override
    public void delete(long id) {
        Role entity = find(id);
        delete(entity);
    }
}
