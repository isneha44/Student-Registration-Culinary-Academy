package com.culinarry.registration.config;

import com.culinarry.registration.entity.Program;
import com.culinarry.registration.entity.Registration;
import com.culinarry.registration.entity.Student;
import com.culinarry.registration.entity.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Load properties from hibernate.properties
                Properties properties = new Properties();
                properties.load(new FileInputStream(new File("src/com/culinarry/registration/resources/hibernate.properties")));

                Configuration configuration = new Configuration();
                configuration.setProperties(properties);

                // Add annotated entity classes
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Program.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Registration.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
