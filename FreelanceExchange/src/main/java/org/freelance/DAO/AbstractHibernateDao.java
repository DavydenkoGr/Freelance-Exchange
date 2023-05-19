package org.freelance.DAO;

import java.util.List;

public interface AbstractHibernateDao<T> {
    T find(long id);

    List<T> findAll();

    T create(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(long id);
}
