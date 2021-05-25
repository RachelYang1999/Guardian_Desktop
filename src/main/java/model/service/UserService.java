package model.service;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import model.domain.Entity;
import model.domain.User;
import util.GuardianAPIStrategy;


public class UserService{
    private EntityFactory entityFactory;
    private EntityFactory defaultErrorFactory;
    private User user;
    private GuardianAPIStrategy guardianAPIStrategy;

    public UserService(GuardianAPIStrategy guardianAPIStrategy) {
        this.guardianAPIStrategy = guardianAPIStrategy;
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
    }

    public Entity login(String token) {
        return null;
    }
}
