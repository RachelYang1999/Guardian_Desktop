package factory.entityfactory;

import model.Entity;
import model.PurchaseLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseLoactionFactory implements EntityFactory {


    @Override
    public Entity createEntity(String response) {

        JSONObject jsonObject = new JSONObject(response);

        PurchaseLocation purchaseLocation = new PurchaseLocation();
        purchaseLocation.setSystem(jsonObject.getString("system"));
        purchaseLocation.setLocation(jsonObject.getString("location"));
        purchaseLocation.setPrice(jsonObject.getInt("price"));
        return purchaseLocation;
    }

    @Override
    public List<Entity> createEntities(String response) {
        // Pass a string type json array
        List<Entity> result = new ArrayList<>();
        JSONArray loanArray = new JSONArray(response);
        for (int i = 0; i < loanArray.length(); i ++) {
            result.add(new PurchaseLoactionFactory().createEntity(loanArray.get(i).toString()));
        }
        return result;
    }
}
