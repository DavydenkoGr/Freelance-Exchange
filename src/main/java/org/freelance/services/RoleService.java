package org.freelance.services;

import org.freelance.DAO.RoleDao;
import org.freelance.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Role service which provides data transfer between role dao and other classes
 * Send requests to RoleDao
 */
@Service
public class RoleService implements AbstractService<Role>{
    @Autowired
    protected RoleDao DAO;

    /**
     * Send find request
     * @param id role id
     * @return found role or null if not exist
     */
    @Override
    public Role find(long id) {
        return DAO.find(id);
    }

    /**
     * Send find by name request
     * @param name role name
     * @return found role
     */
    public Role findByName(String name) {
        return DAO.findByName(name);
    }

    /**
     * Send request to find all roles
     * @return found roles list
     */
    @Override
    public List<Role> findAll() {
        return DAO.findAll();
    }

    /**
     * Send save request
     * @param entity role instance
     * @return saved role
     */
    @Override
    public Role create(Role entity) {
        return DAO.create(entity);
    }

    /**
     * Send save or update request
     * @param entity role instance
     */
    @Override
    public void update(Role entity) {
        DAO.update(entity);
    }

    /**
     * Send delete request
     * @param entity role instance
     */
    @Override
    public void delete(Role entity) {
        DAO.delete(entity);
    }

    /**
     * Send delete by id request
     * @param id role id
     */
    @Override
    public void delete(long id) {
        DAO.delete(id);
    }
}
