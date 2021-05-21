package factory.entityfactory;

import model.Entity;
import model.ExistLoan;
import model.User;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginUserFactory implements EntityFactory {

    public User createSignUpUser(String response) {
        if (response.equals("")) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(response);

        String token = jsonObject.getString("token");

        JSONObject userObject = jsonObject.getJSONObject("user");
//        System.out.println(userObject.toString());
        String responseUserName = userObject.getString("username");

        User user = new User();
        user.setToken(token);
        user.setUsername(responseUserName);

        return user;
    }

    @Override
    public Entity createEntity(String response) {
        if (response.equals("")) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(response);
        JSONObject userObject = jsonObject.getJSONObject("user");
        String responseUserName = userObject.getString("username");
        int credits = userObject.getInt("credits");

        ExistLoanFactory existLoanFactory = new ExistLoanFactory();
        List<ExistLoan> loans = new ArrayList<>() ;
        List<Entity> entityTypeLoan = existLoanFactory.createEntities(userObject.getJSONArray("loans").toString());
        for (Entity e : entityTypeLoan) {

            loans.add((ExistLoan) e);
        }

        User user = new User();
        user.setUsername(responseUserName);
        user.setCredit(credits);
        user.setLoginStatus(true);
        user.setLoans(loans);
        return user;
    }

    @Override
    public List<Entity> createEntities(String response) {
        return null;
    }
}
