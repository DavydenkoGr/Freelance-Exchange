package org.freelance.services;

import org.freelance.DAOs.AbstractHibernateDao;
import org.freelance.DAOs.EmployerDao;
import org.freelance.models.Employer;

public class EmployerService extends AbstractService<Employer> {
    protected AbstractHibernateDao<Employer> DAO = new EmployerDao();
}
