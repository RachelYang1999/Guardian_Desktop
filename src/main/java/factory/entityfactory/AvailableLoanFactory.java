package factory.entityfactory;

import model.AvailableLoan;
import model.Entity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableLoanFactory implements EntityFactory {


    @Override
    public Entity createEntity(String response) {
        if (response.equals("")) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(response);

        AvailableLoan loan = new AvailableLoan();
        loan.setAmount(jsonObject.getInt("amount"));
        loan.setRate(jsonObject.getInt("rate"));
        loan.setCollateralRequired(jsonObject.getBoolean("collateralRequired"));
        loan.setType(jsonObject.getString("type"));
        return loan;
    }

    @Override
    public List<Entity> createEntities(String response) {
        List<Entity> result = new ArrayList<>();
        JSONArray loanArray = new JSONArray(response);
        for (int i = 0; i < loanArray.length(); i ++) {
            result.add(new AvailableLoanFactory().createEntity(loanArray.get(i).toString()));
        }
        return result;
    }
}
