package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
        System.out.println("Была создана таблица пользователей");
    }

    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
        System.out.println("Таблица пользователей удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("Был сохранен пользователь " + name);
    }

    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
        System.out.println("Был удален id пользователя");
    }

    @Override
    public List<User> getAllUsers() {
        return  userDaoHibernate.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
        System.out.println("Таблица пользователей очищена");
    }
}
