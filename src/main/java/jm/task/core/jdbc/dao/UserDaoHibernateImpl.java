package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;
import static jm.task.core.jdbc.util.Util.sessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionfactory = getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionfactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createSQLQuery ("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(25) NOT NULL, lastName VARCHAR(25) NOT NULL, age INT NOT NULL)");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionfactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createSQLQuery ("DROP TABLE IF EXISTS user");
            query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("From User", User.class);
            userList = query.getResultList();
            for (User user : userList) {
                System.out.println(user);
            }
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            Query<User> query = session.createSQLQuery ("DELETE from test.user");
            query.executeUpdate();
            transaction.commit();

        }
        catch (HibernateException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
