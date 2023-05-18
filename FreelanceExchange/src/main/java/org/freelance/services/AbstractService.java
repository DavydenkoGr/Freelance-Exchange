package org.freelance.services;

import java.util.List;

public interface AbstractService<T> {
    T find(long id);

    List<T> findAll();

    T create(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(long id);
}
