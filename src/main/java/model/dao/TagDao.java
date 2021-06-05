package model.dao;

import model.domain.Article;
import model.domain.Entity;
import model.domain.Tag;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a Database Access Objects class which includes CRUD operations of article data from TAG table in the database
 * @author Rachel Yang
 */
public class TagDao extends AbstractDao {
  Connection connection;
  Statement statement;

  /**
   * The constructor of Tag Database Access Object
   * @param daoUtil The Database Access Object Util to be injected into the construction of DAO objects for database connection
   */
  public TagDao(DaoUtil daoUtil) {
    super(daoUtil);
    connection = daoUtil.getDatabaseConnection();
  }

  /**
   * Add TAG information to the TAG table of the database
   * @param entity The Tag Entity to be added to the database
   * @return The status of the adding operation
   */
  @Override
  public boolean addEntity(Entity entity) {
    String finalSQL = "";
    Tag tag = null;
    try {
      statement = connection.createStatement();
      String sql =
          "CREATE TABLE IF NOT EXISTS TAG"
              + "(KEYWORDTAG CHAR(120)   PRIMARY KEY    NOT NULL,"
              + " KEYWORD CHAR(50)                        NOT NULL,"
              + " TAGNAME CHAR(50)                        NOT NULL,"
              + " INFO CHAR(1200));";
      statement.executeUpdate(sql);

      if (entity.getEntityType().equals("Tag")) {
        tag = (Tag) entity;
        sql =
            "INSERT INTO TAG (KEYWORDTAG, KEYWORD, TAGNAME, INFO) "
                + "VALUES ('"
                + tag.getRelatedKeyword() + "/" + tag.getTagName().replace("'", "")
                + "', '"
                + tag.getRelatedKeyword().replace("'", "")
                + "', '"
                + tag.getTagName().replace("'", "")
                + "', '"
                + tag.getEntityInformation().replace("'", "")
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
          "Database add tag failed when adding " + entity.getEntityInformation());
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Get tag information from TAG table in the database
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
              "CREATE TABLE IF NOT EXISTS TAG"
                      + "(KEYWORDTAG CHAR(120)   PRIMARY KEY    NOT NULL,"
                      + " KEYWORD CHAR(50)                        NOT NULL,"
                      + " TAGNAME CHAR(50)                        NOT NULL,"
                      + " INFO CHAR(1200));";
      statement.executeUpdate(sql);

      statement = connection.createStatement();
      sql = "SELECT * FROM TAG " + "WHERE " + matchField + " = '" + matchValue + "';";
      //            System.out.println("sql: " + sql);
      statement.executeUpdate(sql);
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        String retrievedResult = resultSet.getString(retrieveFiled);
        result.add(retrievedResult);
        //                System.out.println(retrieveFiled + ": " + retrievedResult);
      }
    } catch (Exception e) {
      System.err.println("Database get tag failed!");
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Update article information to the database
   * @param pk The pk of a row to be updated
   * @param entityInfo The field value to be updated in the related field
   * @return
   */
  @Override
  public boolean updateEntity(String pk, String entityInfo) {
    try {
      statement = connection.createStatement();
      String sql = "UPDATE TAG SET INFO = '" + entityInfo + "' WHERE KEYWORDTAG = '" + pk + "';";
      statement.executeUpdate(sql);
      return true;
    } catch (Exception e) {
      System.err.println("Database update TAG failed!");
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      e.printStackTrace();
      System.exit(0);
    }
    return false;
  }

  /**
   * Delete tag information from the database
   * @param pk
   * @return
   */
  @Override
  public boolean deleteEntity(String pk) {
    try {
      statement = connection.createStatement();
      String sql = "DELETE from USER where KEYWORDTAG = '" + pk + "'; ";
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
    AbstractDao tagDao = new TagDao(new DaoUtil());
    Tag tag = new Tag();
    tag.setRelatedKeyword("keyword");
    tag.setInfo("info");
    tag.setTagName("tag name");

    tagDao.addEntity(tag);

    List<String> info = tagDao.getEntity("KEYWORD", "keyword", "INFO");
    for (String s : info) {
      System.out.println("[TagDao main]");
      System.out.println(s);

    }
  }
}
