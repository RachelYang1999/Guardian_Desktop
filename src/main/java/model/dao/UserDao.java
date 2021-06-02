package model.dao;

import model.domain.Entity;
import model.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao {
    Connection connection;
    Statement statement;


    public UserDao(DaoUtil daoUtil) {
        super(daoUtil);
        connection = daoUtil.getDatabaseConnection();
    }

    @Override
    public boolean addEntity(Entity entity) {
        User user = null;
        try {
            statement = connection.createStatement();
            String sql =
                    "CREATE TABLE IF NOT EXISTS USER" +
                    "(TOKEN CHAR(50) PRIMARY KEY    NOT NULL," +
                    "INFO CHAR(300))";
            statement.executeUpdate(sql);

            if (entity.getEntityType().equals("User")) {
                user = (User) entity;
                sql =
                        "INSERT INTO USER (TOKEN, INFO) " +
                        "VALUES ('" + user.getToken() + "', '" + user.getEntityInformation() + "');";
                statement.executeUpdate(sql);
            }
            statement.close();
            return true;
        } catch (Exception e) {
            System.err.println("Database add user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getEntity(String filed, String entityName) {
        List<String> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String sql =
                    "SELECT * FROM USER " +
                    "WHERE " + filed + " = '" + entityName + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String token = resultSet.getString("TOKEN");
                String info = resultSet.getString("INFO");
//                System.out.println("Token: " + token);
//                System.out.println("Info: " + info);
                result.add(info);
            }
        } catch (Exception e) {
            System.err.println("Database get user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateEntity(String token, String entityInfo) {
        try {
            statement = connection.createStatement();
            String sql =
                    "UPDATE USER SET INFO = '" + entityInfo + "' WHERE TOKEN = '" + token + "';";
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println("Database update user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }

    @Override
    public boolean deleteEntity(String entityName) {
        try {
            statement = connection.createStatement();
            String sql =
                    "DELETE from USER where TOKEN = '" + entityName + "'; ";
            statement.executeUpdate(sql);
            System.out.println("Deleted");
            return true;
        } catch (Exception e) {
            System.err.println("Database delete user failed!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        AbstractDao userDao = new UserDao(new DaoUtil());
        User user = new User();
        user.setToken("fake token1");
        user.setUserTier("dad");
//        userDao.addEntity(user);
        List<String> info = userDao.getEntity("TOKEN", "fake token1");

//        userDao.updateEntity("fake token1", "new fake token1");
//        userDao.deleteEntity("fake token1");

    }
}
