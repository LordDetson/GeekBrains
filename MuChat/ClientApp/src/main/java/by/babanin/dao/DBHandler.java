package by.babanin.dao;

import by.babanin.entity.User;

import java.sql.*;

import static by.babanin.dao.ConstantDao.*;

public class DBHandler {
    private ConfigDao config = ConfigDao.getInstance();
    private Connection connection;

    private static class Singleton {
        static DBHandler INSTANCE = new DBHandler();
    }

    public static DBHandler getInstance() {
        return Singleton.INSTANCE;
    }

    private DBHandler() {
        String url = "jdbc:mysql://" + config.getHost() + ":" +
                config.getPort() + "/" + config.getNameDB() +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            connection = DriverManager.getConnection(url, config.getUser(), config.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registrationUser(User user) {

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getGender());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String login, String password) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt(USER_ID),
                        resultSet.getString(USER_FIRST_NAME),
                        resultSet.getString(USER_LAST_NAME),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_GENDER)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser(String login) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt(USER_ID),
                        resultSet.getString(USER_FIRST_NAME),
                        resultSet.getString(USER_LAST_NAME),
                        resultSet.getString(USER_LOGIN),
                        resultSet.getString(USER_PASSWORD),
                        resultSet.getString(USER_GENDER)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    static class Test {
        public static void main(String[] args) throws SQLException {
            DBHandler dbHandler = DBHandler.getInstance();
            System.out.println(dbHandler.getUser("Lord_Detson", "123"));
        }
    }
}
