package model.domain;

/**
 * This is an interface for defining general entities in this application
 * @author Rachel Yang
 */
public interface Entity {

  /**
   * Get the information about corresponding entity object
   * @return Information about corresponding entity object
   */
  String getEntityInformation();

  /**
   * Get the specific entity type of corresponding entity object
   * @return Specific entity type of corresponding entity object
   */
  String getEntityType();
}
