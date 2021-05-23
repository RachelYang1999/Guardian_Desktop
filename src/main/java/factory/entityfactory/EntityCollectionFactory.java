package factory.entityfactory;

import model.domain.Entity;

import java.util.List;

public interface EntityCollectionFactory {

    List<Entity> createEntities(String response);


}
