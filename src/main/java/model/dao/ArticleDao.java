package model.dao;

import model.domain.Article;
import model.domain.Entity;
import model.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a Database Access Objects class which includes CRUD operations of article data from ARTICLE table in the database
 * @author Rachel Yang
 */
public class ArticleDao extends AbstractDao {
  Connection connection;
  Statement statement;

  /**
   * The constructor of Article Database Access Object
   * @param daoUtil The Database Access Object Util to be injected into the construction of DAO objects for database connection
   */
  public ArticleDao(DaoUtil daoUtil) {
    super(daoUtil);
    connection = daoUtil.getDatabaseConnection();
  }

  /**
   * Add ARTICLE information to the ARTICLE table of the database
   * @param entity The Article Entity to be added to the database
   * @return The status of the adding operation
   */
  @Override
  public boolean addEntity(Entity entity) {
    String finalSQL = "";
    Article article = null;
    try {
      statement = connection.createStatement();
      String sql =
          "CREATE TABLE IF NOT EXISTS ARTICLE"
              + "(TAGGEDID CHAR(120)   PRIMARY KEY    NOT NULL,"
              + " ID CHAR(80)                          NOT NULL,"
              + " TAG CHAR(50)                        NOT NULL,"
              + " TITLE CHAR(100)                     NOT NULL,"
              + " INFO CHAR(1200));";
      statement.executeUpdate(sql);

      if (entity.getEntityType().equals("Article")) {
        article = (Article) entity;
        sql =
            "INSERT INTO ARTICLE (TAGGEDID, ID, TAG, TITLE, INFO) "
                + "VALUES ('"
                + article.getRelatedTag() + "/" + article.getId().replace("'", "")
                + "', '"
                + article.getId().replace("'", "")
                + "', '"
                + article.getRelatedTag().replace("'", "")
                + "', '"
                + article.getWebTitle().replace("'", "")
                + "', '"
                + article.getEntityInformation().replace("'", "")
                + "');";
        finalSQL = sql;
        System.out.println("sql: " + sql);
        statement.executeUpdate(sql);
      }
      statement.close();
      return true;
    } catch (Exception e) {
      System.err.println("SQL: " + finalSQL);

      System.err.println(
          "Database add article failed when adding " + entity.getEntityInformation());
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Get article information from ARTICLE table in the database
   * @param matchField The field to be matched
   * @param matchValue The field value to be matched
   * @param retrieveFiled The field to be retrieved
   * @return The list of matched filed values
   */
  @Override
  public List<String> getEntity(String matchField, String matchValue, String retrieveFiled) {
    List<String> result = new ArrayList<>();
    //        String result = "";
    try {
      statement = connection.createStatement();
      String sql =
              "CREATE TABLE IF NOT EXISTS ARTICLE"
                      + "(TAGGEDID CHAR(120)   PRIMARY KEY    NOT NULL,"
                      + " ID CHAR(80)                          NOT NULL,"
                      + " TAG CHAR(50)                        NOT NULL,"
                      + " TITLE CHAR(100)                     NOT NULL,"
                      + " INFO CHAR(1200));";
      statement.executeUpdate(sql);

      statement = connection.createStatement();
      sql = "SELECT * FROM ARTICLE " + "WHERE " + matchField + " = '" + matchValue + "';";
      //            System.out.println("sql: " + sql);
      statement.executeUpdate(sql);
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        String retrievedResult = resultSet.getString(retrieveFiled);
        result.add(retrievedResult);
        //                System.out.println(retrieveFiled + ": " + retrievedResult);
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
   * @param pk The field to be updated
   * @param entityInfo The field value to be updated in the related field
   * @return
   */
  @Override
  public boolean updateEntity(String pk, String entityInfo) {
    try {
      statement = connection.createStatement();
      String sql = "UPDATE ARTICLE SET INFO = '" + entityInfo + "' WHERE TAGGEDID = '" + pk + "';";
//      String sql = "UPDATE USER SET INFO = '" + entityInfo + "' WHERE TOKEN = '" + token + "';";
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
   * Delete article information from the database
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

  public static void main(String[] args) {
    AbstractDao articleDAO = new ArticleDao(new DaoUtil());
    Entity entity = new Article();
    articleDAO.addEntity(entity);

    //        String info = articleDAO.getEntity("TAG", "gay couple");
    //        System.out.println("info " + info);
    //        System.out.println(info.equals(""));

    //        userDao.updateEntity("fake token1", "new fake token1");
    //        userDao.deleteEntity("fake token1");
  }
}
