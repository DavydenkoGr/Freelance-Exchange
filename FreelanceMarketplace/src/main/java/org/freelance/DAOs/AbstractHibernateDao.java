package org.freelance.DAOs;

import com.google.common.base.Preconditions;
import org.freelance.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T> {

    public abstract T find(long id);

    @SuppressWarnings("unchecked")
    public abstract List<T> findAll();

    public T create(T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        Preconditions.checkNotNull(entity);
        return getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().remove(entity);
    }

    public void delete(long id) {
        T entity = find(id);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
    }
}
