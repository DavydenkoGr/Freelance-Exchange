package org.freelance.DAO;

import java.util.List;

/**
 * Interface for dao object which organize work with database using SessionFactory object
 * @param <T> parametrized model class
 */
public interface AbstractHibernateDao<T> {
    /**
     * Find entity by id in database
     * @param id entity id
     * @return found entity or null if not exist
     */
    T find(long id);

    /**
     * Find all entities in database table
     * @return found entities list
     */
    List<T> findAll();

    /**
     * Save entity instance to database
     * @param entity instance
     * @return entity
     */
    T create(T entity);

    /**
     * Save or update entity instance
     * @param entity instance
     */
    void update(T entity);

    /**
     * Delete entity instance from database
     * @param entity instance
     */
    void delete(T entity);

    /**
     * Delete entity instance from database
     * @param id entity id
     */
    void delete(long id);
}
