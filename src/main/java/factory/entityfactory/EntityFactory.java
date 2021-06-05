package factory.entityfactory;

import model.domain.Entity;
import org.json.JSONObject;

/**
 * The entity factory interface for producing different type of entity
 * @author Rachel Yang
 */
public interface EntityFactory {

  /**
   *
   * @param response The response from API or dummy API in the JSON format
   * @return The entity which stores the information of different input response
   */
  Entity createEntity(JSONObject response);
}
