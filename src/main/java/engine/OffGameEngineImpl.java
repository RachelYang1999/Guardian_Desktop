package engine;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import factory.entityfactory.LoginUserFactory;
import model.Entity;
import model.User;
import org.json.JSONObject;
import utility.GuardianOnlineUtil;

public class OffGameEngineImpl implements GameEngine {
    private EntityFactory entityFactory;
    private EntityFactory defaultErrorFactory;
    private User user;
    private GuardianOnlineUtil guardianOnlineUtil;

    public OffGameEngineImpl() {
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
    }

    @Override
    public Entity login(String token) {
        return null;
    }

}
