package factory.entityfactory;

import model.Cargo;
import model.Entity;
import model.ExistShip;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExistShipFactory implements EntityFactory {
//    {
//        "credits": 157750,
//        "ship": {
//            "id": "ckopr7xek594591bs6xwg7j6if",
//            "location": "OE-PM-TR",
//            "x": 21,
//            "y": -24,
//            "cargo": [],
//            "spaceAvailable": 50,
//            "type": "JW-MK-I",
//            "class": "MK-I",
//            "maxCargo": 50,
//            "speed": 1,
//            "manufacturer": "Jackshaw",
//            "plating": 5,
//            "weapons": 5
//        }
//    }
    @Override
    public Entity createEntity(String response) {
        JSONObject jsonObject = new JSONObject(response);

        ExistShip existShip = new ExistShip();
        existShip.setId(jsonObject.getString("id"));
        existShip.setLocation(jsonObject.getString("location"));
        existShip.setxCoord(jsonObject.getInt("x"));
        existShip.setyCoord(jsonObject.getInt("y"));

        existShip.setSpaceAvailable(jsonObject.getInt("spaceAvailable"));
        existShip.setType(jsonObject.getString("type"));
        existShip.setShipClass(jsonObject.getString("class"));
        existShip.setMaxCargo(jsonObject.getInt("maxCargo"));
        existShip.setSpeed(jsonObject.getInt("speed"));
        existShip.setManufacturer(jsonObject.getString("manufacturer"));
        existShip.setPlating(jsonObject.getInt("plating"));
        existShip.setWeapons(jsonObject.getInt("weapons"));

        /*
        ------------------------------------------------------------------------
        Dummy and need to be added after cargo class being implemented
         */
        existShip.addCargo(new Cargo());
        /*
        ------------------------------------------------------------------------
         */

//        PurchaseLoactionFactory purchaseLoactionFactory = new PurchaseLoactionFactory();
//
//        List<Entity> purchaseLocationListEntity = purchaseLoactionFactory.createEntities(jsonObject.getJSONArray("purchaseLocations").toString());
//        List<PurchaseLocation> purchaseLocationList = new ArrayList<>();
//        for (Entity e : purchaseLocationListEntity) {
////            System.out.println("AvailableShipFactory createEntity(): purchaseLocationListEntity.getInfo:\n");
////            System.out.println(e.getEntityInformation());
//            purchaseLocationList.add((PurchaseLocation) e);
//        }
//        availableShip.setPurchaseLocationList(purchaseLocationList);

        return existShip;
    }

    @Override
    public List<Entity> createEntities(String response) {
        List<Entity> result = new ArrayList<>();
        System.out.println("[ExistShipFactory] response is: " + response);
        JSONArray shipArray = new JSONArray(response);
        for (int i = 0; i < shipArray.length(); i ++) {
            result.add(new ExistShipFactory().createEntity(shipArray.get(i).toString()));
        }
        return result;
    }
}
