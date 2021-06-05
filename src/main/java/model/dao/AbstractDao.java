package model.dao;

import model.domain.Entity;

import java.sql.Connection;
import java.util.List;

/**
 * This is an abstract class of Database Access Objects which includes CRUD operations of data from the database
 * @author Rachel Yang
 */
public abstract class AbstractDao {

  protected Connection connection;
  protected DaoUtil daoUtil;

  /**
   * The constructor of Database Access Objects
   * @param daoUtil The Database Access Object Util to be injected into the construction of DAO objects for database connection
   */
  public AbstractDao(DaoUtil daoUtil) {
    this.daoUtil = daoUtil;
  }

  /**
   * The getter method for the injected DaoUtil object
   * @return The injected Database Access Object Util
   */
  public DaoUtil getDaoUtil() {
    return this.daoUtil;
  }

  /**
   * Add entity information to the database
   * @param entity The Entity to be added to the database
   * @return The status of the adding operation
   */
  public abstract boolean addEntity(Entity entity);

  /**
   * Get entity information from the database
   * @param matchField The field to be matched
   * @param matchValue The field value to be matched
   * @param retrieveFiled The field to be retrieved
   * @return The list of matched filed values
   */
  public abstract List<String> getEntity(
      String matchField, String matchValue, String retrieveFiled);

  /**
   * Update entity information to the database
   * @param entityName The field to be updated
   * @param entityInfo The field value to be updated in the related field
   * @return
   */
  public abstract boolean updateEntity(String entityName, String entityInfo);

  /**
   * Delete entity information from the database
   * @param entityName
   * @return
   */
  public abstract boolean deleteEntity(String entityName);
}
