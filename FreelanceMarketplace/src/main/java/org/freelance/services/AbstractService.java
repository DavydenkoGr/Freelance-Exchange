package org.freelance.services;

import org.freelance.DAOs.AbstractHibernateDao;

public abstract class AbstractService<T> {
    protected AbstractHibernateDao<T> DAO;

    public T create(T entity) {
        return DAO.create(entity);
    }

    public T update(T entity) {
        return DAO.update(entity);
    }

    public void delete(T entity) {
        DAO.delete(entity);
    }

    public void delete(long id) {
        DAO.delete(id);
    }
}
