package org.freelance.DAOs;

import com.google.common.base.Preconditions;
import org.freelance.HibernateSessionFactoryUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable> {
    private Class<T> clazz;

    public final void setClazz(Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public T find(long id) {
        return getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) getCurrentSession().createQuery("FROM " + clazz.getName()).list();
    }

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
