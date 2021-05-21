package factory.entityfactory;

import model.Entity;

import java.util.List;

public interface EntityFactory {

    Entity createEntity(String response);

    List<Entity> createEntities(String response);


}
