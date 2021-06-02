package model.service;

import factory.entityfactory.*;
import model.dao.AbstractDao;
import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.domain.Article;
import model.domain.Entity;
import model.domain.User;
import org.json.JSONObject;
import util.GuardianAPIStrategy;
import util.GuardianOnlineAPIStrategy;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    private EntityCollectionFactory entityCollectionFactory;
    private EntityFactory defaultErrorFactory;
    private EntityFactory entityFactory;
    private User user;
    private GuardianAPIStrategy guardianAPIStrategy;

    private AbstractDao articleDao;


    public ArticleService(GuardianAPIStrategy guardianAPIStrategy, AbstractDao articleDao) {
        this.guardianAPIStrategy = guardianAPIStrategy;
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
        this.entityCollectionFactory = new ArticleFactory();
        this.articleDao = articleDao;
    }

    public List<Entity> getAllArticles(String token, String tag) {
        List<Entity> entities = new ArrayList<>();

        JSONObject responseJSON = guardianAPIStrategy.searchByTag(token, tag, 1);
        if (responseJSON == null) {
            System.out.println("[UserService] searchByTag responseJSON is null");
        }
        System.out.println("[UserService] searchByTag response: " + responseJSON.toString());
        if (responseJSON.has("response")) {
            int totalPageCount = responseJSON.getJSONObject("response").getInt("pages");
            for (int i = 1; i <= totalPageCount; i ++) {
                List<Entity> singlePageEntities = searchByTag(token, tag, i);

                entities.addAll(singlePageEntities);
//            entities.Collections.addAll(singlePageEntities);
                System.out.println("----------------------------------------");
                System.out.println("[UserService] finished add result page " + i);
                System.out.println("----------------------------------------");
            }
        } else {
            entities.addAll(searchByTag(token, tag, 1));
        }

        return entities;
    }

    public List<Entity> searchByTag(String token, String tag, int pageNUmber) {
        List<Entity> entities = new ArrayList<>();

        return entities;
    }


    public static void main(String[] args) {
        System.out.println("Main---------------------------------------------");

//        List<Entity> entities = new ArticleService(new GuardianOnlineAPIStrategy(), new DaoUtil()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay couple");
//        List<Entity> entities = new ArticleService(new GuardianOfflineAPIStrategy()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay couple");
//        List<Entity> entities = new ArticleService(new GuardianOnlineAPIStrategy()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "football");
//        List<Entity> entities = new ArticleService(new GuardianOnlineAPIStrategy()).getAllArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "chinese food");

//        System.out.println("Main Entity Information---------------------------------------------");
//        for (Entity e : entities) {
//            System.out.println(e.getEntityInformation());
//        }
//        System.out.println("Main Entity Information End---------------------------------------------");

    }

}
