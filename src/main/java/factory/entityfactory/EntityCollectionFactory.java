package factory.entityfactory;

import model.domain.Entity;
import org.json.JSONObject;

import java.util.List;

public interface EntityCollectionFactory {

    List<Entity> createEntities(JSONObject response);


}
