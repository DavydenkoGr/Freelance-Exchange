package org.freelance;

import org.freelance.models.Role;
import org.freelance.models.Task;
import org.freelance.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * Util which creates SessionFactory bean
 */
@org.springframework.context.annotation.Configuration
public class HibernateSessionFactoryUtil {
    /**
     * Instantiate SessionFactory object to work with database
     * @return SessionFactory instance
     */
    @Bean(name="entityManagerFactory")
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();

            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Task.class);

            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
            );

        } catch (Exception exception) {
            throw new RuntimeException("cannot create db session");
        }
    }
}
