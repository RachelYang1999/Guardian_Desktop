package model.service;

import factory.entityfactory.*;
import model.dao.AbstractDao;
import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.dao.TagDao;
import model.domain.Entity;
import model.domain.Tag;
import model.domain.User;
import org.json.JSONObject;
import util.GuardianAPIStrategy;
import util.GuardianOnlineAPIStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * The Model Service of the Entity Tag which encapsulated the business logic for searching articles by input tag from the API
 * @author Rachel Yang
 */
public class TagService {

  private EntityCollectionFactory entityCollectionFactory;
  private EntityFactory defaultErrorFactory;
  private EntityFactory entityFactory;
  private User user;
  private GuardianAPIStrategy guardianAPIStrategy;

  private AbstractDao tagDao;
  private AbstractDao articleDao;

  /**
   * The constructor of TagService
   * @param guardianAPIStrategy The API fetching strategy to be injected into the construction of ArticleService
   * @param articleDao The Tag Database Access Object to be injected into the construction of ArticleService,
   *                   which will be used for CURD operation between API response and the database
   */
  public TagService(GuardianAPIStrategy guardianAPIStrategy, AbstractDao tagDao, AbstractDao articleDao) {
    this.guardianAPIStrategy = guardianAPIStrategy;
    this.defaultErrorFactory = new DefaultErrorInfoFactory();
    this.entityCollectionFactory = new ArticleFactory();
    this.tagDao = tagDao;
    this.articleDao = articleDao;
  }

  /**
   * This method is for search all Tags by the input keyword
   * @param token The token for authorization to search articles from the API
   * @param keyword The keyword which will be matched for searching tags
   * @return The list of entity which store the response information
   */
  public List<Entity> searchAllTagsByKeyword(String token, String keyword) {
    List<Entity> entities = new ArrayList<>();

    JSONObject responseJSON = guardianAPIStrategy.searchTagsByKeyword(token, keyword, 1);
    if (responseJSON == null) {
      System.out.println("[UserService] searchByTag responseJSON is null");
    }
    System.out.println("[UserService] searchByTag response: " + responseJSON.toString());
    if (responseJSON.has("response")) {
      int totalPageCount = responseJSON.getJSONObject("response").getInt("pages");
      for (int i = 1; i <= totalPageCount; i++) {
        List<Entity> singlePageEntities = searchTagsByKeyword(token, keyword, i);

        entities.addAll(singlePageEntities);
        //            entities.Collections.addAll(singlePageEntities);
        System.out.println("----------------------------------------");
        System.out.println("[UserService] finished add result page " + i);
        System.out.println("----------------------------------------");
      }
    } else {
      entities.addAll(searchTagsByKeyword(token, keyword, 1));
    }

    return entities;
  }

  /**
   * The method is for search allA aticles by the input tag
   * @param token The token for authorization to search articles from the API
   * @param keyword The keyword which will be matched for searching articles
   * @param pageNumber The page number which will be fetched for api response
   * @return List of entities which store the response information
   */
  public List<Entity> searchTagsByKeyword(String token, String keyword, int pageNumber) {
    List<Entity> entities = new ArrayList<>();

    JSONObject responseJSON = guardianAPIStrategy.searchTagsByKeyword(token, keyword, pageNumber);
    if (responseJSON == null) {
      System.out.println("[UserService] searchByTag responseJSON is null");
    }
    entities.add(defaultErrorFactory.createEntity(responseJSON)); // Create default error object

    if (responseJSON.has("response")) {
      if (responseJSON.getJSONObject("response").has("status")) {
        if (responseJSON.getJSONObject("response").getString("status").equals("ok")) {
          entityCollectionFactory = new TagFactory();
          entities.clear();
          entities = entityCollectionFactory.createEntities(responseJSON);
          for (Entity e : entities) {
            if (e.getEntityType().equals("Tag")) {
              Tag tag = (Tag) e;
              tag.setRelatedKeyword(keyword);
              // Database operation

              if (tagDao.getEntity("KEYWORDTAG", keyword + "/" + tag.getTagName(), "INFO").size() == 0) {
                tagDao.addEntity(tag);
              } else {
                tagDao.updateEntity(tag.getRelatedKeyword() + "/" + tag.getTagName().replace("'", ""),
                        tag.getEntityInformation().replace("'", ""));
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

  public List<Entity> searchCachedTagsByKeyword(String keyword) {
    List<Entity> entities = new ArrayList<>();
    //        Article article
    List<String> tagNameList = tagDao.getEntity("KEYWORD", keyword, "TAGNAME");
    List<String> infoList = tagDao.getEntity("KEYWORD", keyword, "INFO");

    for (int i = 0; i < tagNameList.size(); i++) {
      if (articleDao.getEntity("TAG", tagNameList.get(i), "INFO").size() != 0) {
        Tag tag = new Tag();
        tag.setRelatedKeyword(keyword);
        tag.setTagName(tagNameList.get(i));
        tag.setInfo(infoList.get(i));
        entities.add(tag);
      }
    }
    System.out.println("[TagService searchCachedTagByKeyword]");
    for (Entity e : entities) {

      System.out.println(e.getEntityInformation());
    }
    return entities;
  }

  public static void main(String[] args) {
    System.out.println("Main---------------------------------------------");
//    List<Entity> entities =
//        new TagService(new GuardianOnlineAPIStrategy(), new TagDao(new DaoUtil()))
//            .searchAllTagsByKeyword("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay couple");

    List<Entity> entities =
            new TagService(new GuardianOnlineAPIStrategy(), new TagDao(new DaoUtil()), new ArticleDao(new DaoUtil()))
                    .searchCachedTagsByKeyword("gay");
  }
}
