package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Db {
    static public Session initialize() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
            return null;
        }
    }
}
