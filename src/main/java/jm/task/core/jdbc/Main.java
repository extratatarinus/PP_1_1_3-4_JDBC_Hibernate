package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Айдар", "Каримов", (byte) 12);
        userService.saveUser("Карим", "Айдаров", (byte) 23);
        userService.saveUser("Залма", "Охзу", (byte) 54);
        userService.saveUser("Аюбджони", "Раджабзода", (byte) 0);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
