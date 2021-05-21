package factory.entityfactory;

import model.Entity;
import model.User;
import org.json.JSONObject;

public class LoginUserFactory implements EntityFactory {

    @Override
    public Entity createEntity(JSONObject response) {
        User user = new User();
        user.setUserTier(response.getJSONObject("response").getString("userTier"));
        return user;
    }
}
