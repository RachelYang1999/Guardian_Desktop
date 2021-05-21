package factory.entityfactory;

import model.Entity;
import model.Response;

import java.util.ArrayList;
import java.util.List;

public class ResponseFactory implements EntityFactory {

    @Override
    public Entity createEntity(String response) {
        Response responseObj = new Response();
        responseObj.setInfo(response);
        return responseObj;
    }

    @Override
    public List<Entity> createEntities(String response) {
        List<Entity> entities = new ArrayList<>();
        Response responseObj = new Response();
        responseObj.setInfo(response);
        entities.add(responseObj);
        return entities;
    }
}
