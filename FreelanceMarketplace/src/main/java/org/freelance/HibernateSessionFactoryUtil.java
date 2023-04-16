package org.freelance;

import org.freelance.models.Employee;
import org.freelance.models.Employer;
import org.freelance.models.Task;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                configuration.addAnnotatedClass(Employer.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Task.class);

                sessionFactory = configuration.buildSessionFactory(
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
                );

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return sessionFactory;
    }
}
