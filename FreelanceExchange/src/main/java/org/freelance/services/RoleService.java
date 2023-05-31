package org.freelance.services;

import org.freelance.DAO.RoleDao;
import org.freelance.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService implements AbstractService<Role>{
    @Autowired
    protected RoleDao DAO;

    @Override
    public Role find(long id) {
        return DAO.find(id);
    }

    public Role findByName(String name) {
        return DAO.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return DAO.findAll();
    }

    @Override
    public Role create(Role entity) {
        return DAO.create(entity);
    }

    @Override
    public void update(Role entity) {
        DAO.update(entity);
    }

    @Override
    public void delete(Role entity) {
        DAO.delete(entity);
    }

    @Override
    public void delete(long id) {
        DAO.delete(id);
    }
}
