package facade;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import factory.entityfactory.ErrorInfoFactory;
import factory.entityfactory.LoginUserFactory;
import model.domain.Entity;
import model.domain.User;
import org.json.JSONObject;
import strategy.GuardianOnlineAPIStrategy;

public class OnlineEngineFacadeImpl implements EngineFacade {
    private EntityFactory entityFactory;
    private EntityFactory defaultErrorFactory;
    private User user;
    private GuardianOnlineAPIStrategy guardianOnlineUtil;

    public OnlineEngineFacadeImpl() {
        this.guardianOnlineUtil = new GuardianOnlineAPIStrategy();
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

        if (responseJSON.has("response")) {
            if (responseJSON.getJSONObject("response").has("status")) {
                if (responseJSON.getJSONObject("response").getString("status").equals("ok")) {
                    entityFactory = new LoginUserFactory();
                    returnEntity = entityFactory.createEntity(responseJSON);
                    this.user = (User) returnEntity;
                    this.user.setToken(token);
                }
            }
        } else if (responseJSON.has("message")) {
            entityFactory = new ErrorInfoFactory();
            returnEntity = entityFactory.createEntity(responseJSON);
        }

        // Default ErrorInfo entity
        return returnEntity;
    }

//    @Override
//    public Entity login(String token) {
//        JSONObject responseJSON = guardianOnlineUtil.login(token);
//        if (responseJSON == null) {
//            System.out.println("[OnlineGameEngineImpl] responseJSON is null: " + responseJSON.toString());
//
//        }
//        Entity returnEntity = defaultErrorFactory.createEntity(responseJSON);
//        System.out.println("[OnlineGameEngineImpl] login response: " + responseJSON.toString());
//
//        if (responseJSON.has("response")) {
//            if (responseJSON.getJSONObject("response").has("status")) {
//                if (responseJSON.getJSONObject("response").getString("status").equals("ok")) {
//                    entityFactory = new LoginUserFactory();
//                    returnEntity = entityFactory.createEntity(responseJSON);
//                    this.user = (User) returnEntity;
//                    this.user.setToken(token);
//                }
//            }
//        } else if (responseJSON.has("message")) {
//            entityFactory = new ErrorInfoFactory();
//            returnEntity = entityFactory.createEntity(responseJSON);
//        }
//
//        // Default ErrorInfo entity
//        return returnEntity;
//    }

    @Override
    public void userLogOut() {
        if (this.user != null) {
            this.user.setLoginStatus(false);
        }
    }

}
