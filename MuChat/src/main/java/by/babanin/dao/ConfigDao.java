package by.babanin.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringJoiner;

public class ConfigDao {
    private String host;
    private String port;
    private String user;
    private String password;
    private String nameDB;

    private static final class Singleton {
        static final ConfigDao INSTANCE = new ConfigDao(ConstantDao.PATH_CONFIG_DAO);
    }

    public static ConfigDao getInstance() {
        return Singleton.INSTANCE;
    }

    private ConfigDao(String pathProperty) {
        File config = new File(pathProperty);
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(config)){
            properties.load(fis);
            host = properties.getProperty("jdbc.host");
            port = properties.getProperty("jdbc.port");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
            nameDB = properties.getProperty("jdbc.namedb");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getNameDB() {
        return nameDB;
    }

    static class Test {
        public static void main(String[] args) {
            ConfigDao config = ConfigDao.getInstance();
            StringJoiner joiner = new StringJoiner("\n")
                    .add(config.getHost())
                    .add(config.getNameDB())
                    .add(config.getPassword())
                    .add(config.getPort())
                    .add(config.getUser());
            System.out.println(joiner);
        }
    }
}
