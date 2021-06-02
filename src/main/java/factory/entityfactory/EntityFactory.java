package factory.entityfactory;

import model.domain.Entity;
import org.json.JSONObject;

public interface EntityFactory {

  Entity createEntity(JSONObject response);
}
