package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    private final Connection connection = util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String querry = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?);";
        try {
            preparedStatement = connection.prepareStatement(querry);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;

        String querry = "DELETE FROM user WHERE `id` = ?";
        try {
            preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String querry = "SELECT * FROM user";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
