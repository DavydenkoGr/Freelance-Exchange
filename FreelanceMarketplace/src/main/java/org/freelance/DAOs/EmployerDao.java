package org.freelance.DAOs;

import org.freelance.models.Employer;

import java.util.List;

public class EmployerDao extends AbstractHibernateDao<Employer>{
    @Override
    public Employer find(long id) {
        return getCurrentSession().get(Employer.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employer> findAll() {
        return (List<Employer>) getCurrentSession().createQuery("FROM employers").list();
    }
}
