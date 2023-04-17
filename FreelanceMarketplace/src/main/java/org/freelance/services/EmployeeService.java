package org.freelance.services;

import org.freelance.DAOs.AbstractHibernateDao;
import org.freelance.DAOs.EmployeeDao;
import org.freelance.models.Employee;

public class EmployeeService {
    protected AbstractHibernateDao<Employee> DAO = new EmployeeDao();
}
