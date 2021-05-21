package factory.entityfactory;

import model.Entity;
import model.MarketPlace;

import java.util.ArrayList;
import java.util.List;

public class MarketPlaceFactory implements EntityFactory {

    @Override
    public Entity createEntity(String response) {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setInfo(response);
        return marketPlace;
    }

    @Override
    public List<Entity> createEntities(String response) {
        List<Entity> entities = new ArrayList<>();
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setInfo(response);
        entities.add(marketPlace);
        return entities;
    }
}
