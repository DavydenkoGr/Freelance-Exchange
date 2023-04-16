package org.freelance.DAOs;

import org.freelance.models.Employee;

import java.util.List;

public class EmployeeDao extends AbstractHibernateDao<Employee> {
    @Override
    public Employee find(long id) {
        return getCurrentSession().get(Employee.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        return (List<Employee>) getCurrentSession().createQuery("FROM employees").list();
    }
}
