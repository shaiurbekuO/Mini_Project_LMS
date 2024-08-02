package main.config;

import jakarta.persistence.EntityManagerFactory;
import main.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseConfig {
    public static EntityManagerFactory entityManagerFactory() {
        try {
            Properties hibernateProperties = new Properties();
            hibernateProperties.put(Environment.DRIVER, "org.postgresql.Driver");
            hibernateProperties.put(Environment.URL, "jdbc:postgresql://localhost:5432/hibernate");
            hibernateProperties.put(Environment.USER, "postgres");
            hibernateProperties.put(Environment.PASS, "1234");
            hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");
            hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            hibernateProperties.put(Environment.SHOW_SQL, "true");
//            hibernateProperties.put(Environment.FORMAT_SQL, "true");


            Configuration configuration = new Configuration();
            configuration.setProperties(hibernateProperties);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Agency.class);
            configuration.addAnnotatedClass(Owner.class);
            configuration.addAnnotatedClass(RentInfo.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(House.class);
            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
