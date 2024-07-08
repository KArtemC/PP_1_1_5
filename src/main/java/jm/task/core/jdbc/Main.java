package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Петр", "Петров", (byte) 20);
        userService.saveUser("Иван", "Иванов", (byte) 30);
        userService.saveUser("Света", "Светикова", (byte) 40);
        userService.saveUser("Рома", "Романов", (byte) 50);

        userService.getAllUsers();
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}

