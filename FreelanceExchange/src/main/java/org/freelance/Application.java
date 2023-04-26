package org.freelance;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
//        SpringApplication.run(Application.class, args);
    }
}
