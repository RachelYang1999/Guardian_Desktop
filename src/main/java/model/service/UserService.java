package model.service;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import factory.entityfactory.ErrorInfoFactory;
import factory.entityfactory.LoginUserFactory;
import model.dao.AbstractDao;
import model.dao.DaoUtil;
import model.dao.UserDao;
import model.domain.Entity;
import model.domain.User;
import org.json.JSONObject;
import util.GuardianAPIStrategy;

public class UserService {
    private EntityFactory entityFactory;
    private EntityFactory defaultErrorFactory;
    private User user;
    private GuardianAPIStrategy guardianAPIStrategy;

    private AbstractDao userDao;

    public UserService(GuardianAPIStrategy guardianAPIStrategy, UserDao userDao) {
        this.guardianAPIStrategy = guardianAPIStrategy;
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
        this.userDao = userDao;
    }

    public Entity login(String token) {
        JSONObject responseJSON = guardianAPIStrategy.login(token);
        if (responseJSON == null) {
            System.out.println("[UserService] login responseJSON is null: " + responseJSON.toString());

        }
        Entity returnEntity = defaultErrorFactory.createEntity(responseJSON);
        System.out.println("[UserService] login response: " + responseJSON.toString());

        if (responseJSON.has("response")) {
            if (responseJSON.getJSONObject("response").has("status")) {
                if (responseJSON.getJSONObject("response").getString("status").equals("ok")) {
                    entityFactory = new LoginUserFactory();
                    returnEntity = entityFactory.createEntity(responseJSON);
                    this.user = (User) returnEntity;
                    this.user.setToken(token);
                    if (userDao.getEntity("TOKEN", token).equals("")) {
                        userDao.addEntity(this.user);
                    } else {
                        userDao.updateEntity(token, user.getEntityInformation());
                    }
                }
            }
        } else if (responseJSON.has("message")) {
            entityFactory = new ErrorInfoFactory();
            returnEntity = entityFactory.createEntity(responseJSON);
        }

        // Default object is ErrorInfo entity
        return returnEntity;
    }
}
