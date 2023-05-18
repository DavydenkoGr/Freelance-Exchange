package org.freelance.DAOs;

import com.google.common.base.Preconditions;
import jakarta.transaction.Transactional;
import org.freelance.models.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RoleDao implements AbstractHibernateDao<Role> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role find(long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        return (List<Role>) sessionFactory.getCurrentSession().createQuery("FROM role").list();
    }

    @Override
    public Role create(Role entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        return entity;
    }

    @Override
    public void update(Role entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
    }

    @Override
    public void delete(Role entity) {
        Preconditions.checkNotNull(entity);
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
    }

    @Override
    public void delete(long id) {
        Role entity = find(id);
        delete(entity);
    }
}
