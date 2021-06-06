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

/**
 * The Model Service of the Entity Article which encapsulated the business logic for searching articles by input tag from the API
 * @author Rachel Yang
 */
public class ArticleService {

  private EntityCollectionFactory entityCollectionFactory;
  private EntityFactory defaultErrorFactory;
  private EntityFactory entityFactory;
  private User user;
  private GuardianAPIStrategy guardianAPIStrategy;

  private AbstractDao articleDao;

  /**
   * The constructor of ArticleController
   * @param guardianAPIStrategy The API fetching strategy to be injected into the construction of ArticleService
   * @param articleDao The Article Database Access Object to be injected into the construction of ArticleService,
   *                   which will be used for CURD operation between API response and the database
   */
  public ArticleService(GuardianAPIStrategy guardianAPIStrategy, AbstractDao articleDao) {
    this.guardianAPIStrategy = guardianAPIStrategy;
    this.defaultErrorFactory = new DefaultErrorInfoFactory();
    this.entityCollectionFactory = new ArticleFactory();
    this.articleDao = articleDao;
  }

  /**
   * Thie method is for search allA aticles by the input tag
   * @param token The token for authorization to search articles from the API
   * @param tag The tag which will be matched for searching articles
   * @return The list of entity which store the response information
   */
  public List<Entity> searchAllArticlesByTag(String token, String tag) {
    List<Entity> entities = new ArrayList<>();

    JSONObject responseJSON = guardianAPIStrategy.searchArticlesByTag(token, tag, 1);
    if (responseJSON == null) {
      System.out.println("[UserService] searchByTag responseJSON is null");
    }
    System.out.println("[UserService] searchByTag response: " + responseJSON.toString());
    if (responseJSON.has("response")) {
      int totalPageCount = responseJSON.getJSONObject("response").getInt("pages");
      for (int i = 1; i <= totalPageCount; i++) {
        List<Entity> singlePageEntities = searchByTag(token, tag, i);

        entities.addAll(singlePageEntities);
//        //            entities.Collections.addAll(singlePageEntities);
//        System.out.println("----------------------------------------");
//        System.out.println("[UserService] finished add result page " + i);
//        System.out.println("----------------------------------------");
      }
    } else {
      entities.addAll(searchByTag(token, tag, 1));
    }

    return entities;
  }

  /**
   * The method is for search allA aticles by the input tag
   * @param token The token for authorization to search articles from the API
   * @param tag The tag which will be matched for searching articles
   * @param pageNUmber The page number which will be fetched for api response
   * @return List of entities which store the response information
   */
  public List<Entity> searchByTag(String token, String tag, int pageNUmber) {
    List<Entity> entities = new ArrayList<>();

    JSONObject responseJSON = guardianAPIStrategy.searchArticlesByTag(token, tag, pageNUmber);
    if (responseJSON == null) {
      System.out.println("[UserService] searchByTag responseJSON is null");
    }
    entities.add(defaultErrorFactory.createEntity(responseJSON)); // Create default error object

    if (responseJSON.has("response")) {
      if (responseJSON.getJSONObject("response").has("status")) {
        if (responseJSON.getJSONObject("response").getString("status").equals("ok")) {
          entityCollectionFactory = new ArticleFactory();
          entities.clear();
          entities = entityCollectionFactory.createEntities(responseJSON);
          for (Entity e : entities) {
            if (e.getEntityType().equals("Article")) {
              Article article = (Article) e;
              article.setRelatedTag(tag);
              if (articleDao.getEntity("TAGGEDID", tag + "/" + article.getId(), "INFO").size() == 0) {
                articleDao.addEntity(article);
              } else {
                articleDao.updateEntity(article.getRelatedTag() + "/" + article.getId().replace("'", ""),
                        article.getEntityInformation().replace("'", ""));
              }
            }
          }
        }
      }
    } else if (responseJSON.has("message")) {
      entities.clear();
      entityFactory = new ErrorInfoFactory();
      entities.add(entityFactory.createEntity(responseJSON));
    } else {
      entities.clear();
      entities.add(this.defaultErrorFactory.createEntity(responseJSON));
    }
    // Default object is ErrorInfo entity list
    return entities;
  }

  /**
   * The method is for search allA aticles by the input tag
   * @param tag The tag which will be matched for searching articles in the database
   * @return List of entities which store the response information
   */
  public List<Entity> searchCachedArticleByTag(String tag) {
    List<Entity> entities = new ArrayList<>();
    //        Article article
    List<String> idList = articleDao.getEntity("TAG", tag, "ID");
    List<String> titleList = articleDao.getEntity("TAG", tag, "TITLE");
    List<String> infoList = articleDao.getEntity("TAG", tag, "INFO");

    for (int i = 0; i < idList.size(); i++) {
      Article article = new Article();
      article.setId(idList.get(i));
      article.setWebTitle(titleList.get(i));
      article.setInfo(infoList.get(i));
      article.setRelatedTag(tag);
      entities.add(article);
    }
    System.out.println("[ArticleService searchByCachedTag]");
    for (Entity e : entities) {

      System.out.println(e.getEntityInformation());
    }
    return entities;
  }
}
