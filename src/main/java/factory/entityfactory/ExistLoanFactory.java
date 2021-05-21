package factory.entityfactory;

import model.Entity;
import model.ExistLoan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExistLoanFactory implements EntityFactory {


    @Override
    public Entity createEntity(String response) {
        if (response.equals("")) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(response);

        ExistLoan loan = new ExistLoan();
        loan.setId(jsonObject.getString("id"));
        loan.setDueDate(jsonObject.getString("due"));
        loan.setRepaymentAmount(jsonObject.getInt("repaymentAmount"));
        loan.setStatus(jsonObject.getString("status"));
        loan.setType(jsonObject.getString("type"));

        return loan;
    }

    @Override
    public List<Entity> createEntities(String response) {
//        {"loans":[{"id":"ckolbp9xo3561241bs6weesqhbk","due":"2021-05-14T10:31:42.726Z","repaymentAmount":280000,"status":"CURRENT","type":"STARTUP"}]}
        List<Entity> result = new ArrayList<>();
        System.out.println("ExistLoanFactory response is: " + response);
        JSONArray loanArray = new JSONArray(response);
        for (int i = 0; i < loanArray.length(); i ++) {
            result.add(new ExistLoanFactory().createEntity(loanArray.get(i).toString()));
        }
        return result;
    }
}
