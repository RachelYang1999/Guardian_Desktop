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

/**
 * This is a Database Access Objects class which includes CRUD operations of article data from USER table in the database
 * @author Rachel Yang
 */
public class UserDao extends AbstractDao {
  Connection connection;
  Statement statement;

  /**
   * The constructor of User Database Access Object
   * @param daoUtil The Database Access Object Util to be injected into the construction of DAO objects for database connection
   */
  public UserDao(DaoUtil daoUtil) {
    super(daoUtil);
    connection = daoUtil.getDatabaseConnection();
  }

  /**
   * Add USER information to the USER table of the database
   * @param entity The User Entity to be added to the database
   * @return The status of the adding operation
   */
  @Override
  public boolean addEntity(Entity entity) {
    User user = null;
    try {
      statement = connection.createStatement();
      String sql =
          "CREATE TABLE IF NOT EXISTS USER"
              + "(TOKEN CHAR(50) PRIMARY KEY    NOT NULL,"
              + "INFO CHAR(300))";
      statement.executeUpdate(sql);

      if (entity.getEntityType().equals("User")) {
        user = (User) entity;
        sql =
            "INSERT INTO USER (TOKEN, INFO) "
                + "VALUES ('"
                + user.getToken()
                + "', '"
                + user.getEntityInformation()
                + "');";
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

  /**
   * Get user information from USER table in the database
   * @param matchField The field to be matched
   * @param matchValue The field value to be matched
   * @param retrieveFiled The field to be retrieved
   * @return The list of matched filed values
   */
  @Override
  public List<String> getEntity(String matchField, String matchValue, String retrieveFiled) {
    List<String> result = new ArrayList<>();
    try {
      statement = connection.createStatement();
      String sql = "SELECT * FROM USER " + "WHERE " + matchField + " = '" + matchValue + "';";
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        String retrieveResult = resultSet.getString(retrieveFiled);
        result.add(retrieveResult);
      }
    } catch (Exception e) {
      System.err.println("Database get user failed!");
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Update article information to the database
   * @param token The field to be updated
   * @param entityInfo The field value to be updated in the related field
   * @return
   */
  @Override
  public boolean updateEntity(String token, String entityInfo) {
    try {
      statement = connection.createStatement();
      String sql = "UPDATE USER SET INFO = '" + entityInfo + "' WHERE TOKEN = '" + token + "';";
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

  /**
   * Delete tag information from the database
   * @param entityName
   * @return
   */
  @Override
  public boolean deleteEntity(String entityName) {
    try {
      statement = connection.createStatement();
      String sql = "DELETE from USER where TOKEN = '" + entityName + "'; ";
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

}
