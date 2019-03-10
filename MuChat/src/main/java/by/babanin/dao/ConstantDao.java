package by.babanin.dao;

public class ConstantDao {
    public static final String USER_TABLE = "user";
    public static final String USER_ID = "id";
    public static final String USER_FIRST_NAME = "firstname";
    public static final String USER_LAST_NAME = "lastname";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_GENDER = "gender";
    public static final String PATH_CONFIG_DAO = "property/configJDBC.properties";
    public static final String INSERT_USER = "INSERT INTO " + USER_TABLE + "(" +
            USER_FIRST_NAME + "," + USER_LAST_NAME + "," + USER_LOGIN + "," +
            USER_PASSWORD + "," + USER_GENDER + ") VALUES(?,?,?,?,?)";

    public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT " +
            USER_ID + ", " + USER_FIRST_NAME + "," + USER_LAST_NAME + "," + USER_LOGIN + "," +
            USER_PASSWORD + "," + USER_GENDER + " FROM " + USER_TABLE + " WHERE " + USER_LOGIN +
            "=? AND " + USER_PASSWORD + "=?";
}
