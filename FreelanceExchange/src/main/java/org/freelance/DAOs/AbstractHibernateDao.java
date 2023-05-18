package org.freelance.DAOs;

import java.util.List;

public interface AbstractHibernateDao<T> {
    public abstract T find(long id);

    public abstract List<T> findAll();

    public T create(T entity);

    public void update(T entity);

    public void delete(T entity);

    public void delete(long id);
}
