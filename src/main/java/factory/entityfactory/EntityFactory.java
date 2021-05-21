package factory.entityfactory;

import model.Entity;
import org.json.JSONObject;

public interface EntityFactory {

    Entity createEntity(JSONObject response);


}
