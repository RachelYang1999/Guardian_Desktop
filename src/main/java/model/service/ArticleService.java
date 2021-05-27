package model.service;

import factory.entityfactory.*;
import model.domain.Entity;
import model.domain.User;
import org.json.JSONObject;
import util.GuardianAPIStrategy;
import util.GuardianOfflineAPIStrategy;
import util.GuardianOnlineAPIStrategy;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    private EntityCollectionFactory entityCollectionFactory;
    private EntityFactory defaultErrorFactory;
    private EntityFactory entityFactory;
    private User user;
    private GuardianAPIStrategy guardianAPIStrategy;

    public ArticleService(GuardianAPIStrategy guardianAPIStrategy) {
        this.guardianAPIStrategy = guardianAPIStrategy;
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
        this.entityCollectionFactory = new ArticleFactory();
    }

    public List<Entity> getAllArticles(String token, String tag) {
        List<Entity> entities = new ArrayList<>();
        return entities;
    }

    public List<Entity> searchByTag(String token, String tag, int pageNUmber) {
        List<Entity> entities = new ArrayList<>();
        return entities;
    }
}
