package factory.entityfactory;

import model.Entity;

import java.util.List;

public interface EntityCollectionFactory {

    List<Entity> createEntities(String response);


}
