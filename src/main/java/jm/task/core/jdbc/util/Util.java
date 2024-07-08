package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USERNAME = "bob";
    private static final String DB_PASSWORD = "1234";

    public static Connection getConnection() {
        Connection сonnection = null;
        try {
            сonnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (!сonnection.isClosed()) {
                System.out.println("Произошло соединение с БД");
            }
        } catch (SQLException e) {
            System.out.println("Нет соединения");
        }
        return сonnection;
    }

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();

            settings.put(AvailableSettings.URL, DB_URL);
            settings.put(AvailableSettings.USER, DB_USERNAME);
            settings.put(AvailableSettings.PASS, DB_PASSWORD);
            settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(HBM2DDL_AUTO, "");

            configuration.setProperties(settings).addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (
            HibernateException e) {
            e.printStackTrace();
            System.out.println("Сеанс не создается");
        }
        return sessionFactory;
    }
}
