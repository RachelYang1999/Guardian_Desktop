package model.dao;

import model.domain.Entity;

import java.sql.Connection;
import java.util.List;

/**
 * This is an abstract class of Database Access Objects
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

  public abstract boolean addEntity(Entity entity);

  public abstract List<String> getEntity(
      String matchField, String matchValue, String retrieveFiled);

  public abstract boolean updateEntity(String entityName, String entityInfo);

  public abstract boolean deleteEntity(String entityName);
}
