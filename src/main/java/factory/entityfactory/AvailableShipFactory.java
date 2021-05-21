package factory.entityfactory;

import model.AvailableShip;
import model.Entity;
import model.PurchaseLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableShipFactory implements EntityFactory {

//        {
//            "type": "HM-MK-I",
//            "class": "MK-I",
//            "maxCargo": 50,
//            "speed": 3,
//            "manufacturer": "Hermes",
//            "plating": 20,
//            "weapons": 5,
//            "purchaseLocations": [
//        {
//            "system": "OE",
//                "location": "OE-PM-TR",
//                "price": 57525
//        }
//            ]
//        },
    @Override
    public Entity createEntity(String response) {
        JSONObject jsonObject = new JSONObject(response);
        AvailableShip availableShip = new AvailableShip();
        availableShip.setType(jsonObject.getString("type"));
        availableShip.setShipClass(jsonObject.getString("class"));
        availableShip.setMaxCargo(jsonObject.getInt("maxCargo"));
        availableShip.setSpeed(jsonObject.getInt("speed"));
        availableShip.setManufacturer(jsonObject.getString("manufacturer"));
        availableShip.setPlating(jsonObject.getInt("plating"));
        availableShip.setWeapons(jsonObject.getInt("weapons"));

        PurchaseLoactionFactory purchaseLoactionFactory = new PurchaseLoactionFactory();

        List<Entity> purchaseLocationListEntity = purchaseLoactionFactory.createEntities(jsonObject.getJSONArray("purchaseLocations").toString());
        List<PurchaseLocation> purchaseLocationList = new ArrayList<>();
        for (Entity e : purchaseLocationListEntity) {
//            System.out.println("AvailableShipFactory createEntity(): purchaseLocationListEntity.getInfo:\n");
//            System.out.println(e.getEntityInformation());
            purchaseLocationList.add((PurchaseLocation) e);
        }
        availableShip.setPurchaseLocationList(purchaseLocationList);

        return availableShip;
    }

    @Override
    public List<Entity> createEntities(String response) {
        // Pass a string type jsonarray
        List<Entity> result = new ArrayList<>();
        System.out.println("Response passed to AvailableShipFactory is: " + response);
        JSONArray loanArray = new JSONArray(response);
        for (int i = 0; i < loanArray.length(); i ++) {
            result.add(new AvailableShipFactory().createEntity(loanArray.get(i).toString()));
        }
        return result;
    }
}
