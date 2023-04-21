package org.freelance.services;

import org.freelance.DAOs.RoleDao;
import org.freelance.models.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleService extends AbstractService<Role>{
    @Autowired
    protected RoleDao DAO;

    public Role find(int id) {
        return DAO.find(id);
    }
}
