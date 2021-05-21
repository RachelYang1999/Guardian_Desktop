package engine;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import factory.entityfactory.ErrorInfoFactory;
import factory.entityfactory.LoginUserFactory;
import model.Entity;
import model.User;
import org.json.JSONObject;
import utility.GuardianOnlineUtil;

public class OnlineGameEngineImpl implements GameEngine {
    private EntityFactory entityFactory;
    private EntityFactory defaultErrorFactory;
    private User user;
    private GuardianOnlineUtil guardianOnlineUtil;

    public OnlineGameEngineImpl() {
        this.guardianOnlineUtil = new GuardianOnlineUtil();
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
    }

    @Override
    public Entity login(String token) {
        JSONObject responseJSON = guardianOnlineUtil.login(token);
        if (responseJSON == null) {
            System.out.println("[OnlineGameEngineImpl] responseJSON is null: " + responseJSON.toString());

        }
        Entity returnEntity = defaultErrorFactory.createEntity(responseJSON);
        System.out.println("[OnlineGameEngineImpl] login response: " + responseJSON.toString());

        if (responseJSON.getJSONObject("response").has("status")) {
            entityFactory = new LoginUserFactory();
            returnEntity = entityFactory.createEntity(responseJSON);
            this.user = (User) returnEntity;
        } else if (responseJSON.has("message")) {
            entityFactory = new ErrorInfoFactory();
            returnEntity = entityFactory.createEntity(responseJSON);
        }
        return returnEntity;
    }

}
