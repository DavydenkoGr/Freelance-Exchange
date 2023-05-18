package org.freelance.DAOs;

import com.google.common.base.Preconditions;
import org.freelance.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface AbstractHibernateDao<T> {
    public abstract T find(long id);

    public abstract List<T> findAll();

    public T create(T entity);

    public void update(T entity);

    public void delete(T entity);

    public void delete(long id);
}
