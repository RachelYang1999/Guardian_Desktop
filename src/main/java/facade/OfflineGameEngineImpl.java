package facade;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import model.domain.Entity;
import model.domain.User;
import strategy.GuardianOnlineAPIStrategy;

public class OfflineGameEngineImpl implements EngineFacade {
    private EntityFactory entityFactory;
    private EntityFactory defaultErrorFactory;
    private User user;
    private GuardianOnlineAPIStrategy guardianOnlineUtil;

    public OfflineGameEngineImpl() {
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
    }

    @Override
    public Entity login(String token) {
        return null;
    }

    @Override
    public void userLogOut() {

    }

}
