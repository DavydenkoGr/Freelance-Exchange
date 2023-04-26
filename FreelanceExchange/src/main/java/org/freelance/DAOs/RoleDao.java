package org.freelance.DAOs;

import org.freelance.models.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDao extends AbstractHibernateDao<Role> {
    @Override
    public Role find(long id) {
        return getCurrentSession().get(Role.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        return (List<Role>) getCurrentSession().createQuery("FROM role").list();
    }
}
