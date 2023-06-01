package org.freelance.services;

import java.util.List;

/**
 * Interface for service which provides data transfer between dao and other classes
 * Send requests to dao
 * @param <T> parametrized model class
 */
public interface AbstractService<T> {
    /**
     * Send find request
     * @param id entity id
     * @return found entity or null if not exist
     */
    T find(long id);

    /**
     * Send request to find all entities
     * @return found entities list
     */
    List<T> findAll();

    /**
     * Send save request
     * @param entity instance
     * @return saved entity
     */
    T create(T entity);

    /**
     * Send save or update request
     * @param entity instance
     */
    void update(T entity);

    /**
     * Send delete request
     * @param entity instance
     */
    void delete(T entity);

    /**
     * Send delete by id request
     * @param id entity id
     */
    void delete(long id);
}
