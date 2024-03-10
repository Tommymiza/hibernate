package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Migration {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            sessionFactory.openSession();
            System.out.println("Database migration successful");
        } catch (Exception e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }
}
