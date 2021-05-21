package factory.entityfactory;

import model.Entity;
import model.User;
import org.json.JSONObject;

import java.util.List;

public class SignupUserFactory implements EntityFactory {
    @Override
    public Entity createEntity(String response) {
        if (response.equals("")) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(response);

        String token = jsonObject.getString("token");

        JSONObject userObject = jsonObject.getJSONObject("user");

        String responseUserName = userObject.getString("username");

        User user = new User();
        user.setToken(token);
        user.setUsername(responseUserName);

        return user;
    }

    @Override
    public List<Entity> createEntities(String response) {
        return null;
    }
}
