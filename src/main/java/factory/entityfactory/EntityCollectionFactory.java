package factory.entityfactory;

import model.domain.Entity;
import org.json.JSONObject;

import java.util.List;

/**
 * The entity list factory interface for producing different type of entities in one response
 * @author Rachel Yang
 */
public interface EntityCollectionFactory {

  /**
   *
   * @param response The response from API or dummy API in the JSON format
   * @return The list of entities which stores the information of the input response
   */
  List<Entity> createEntities(JSONObject response);
}
