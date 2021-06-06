package util;

import controller.ArticleController;
import controller.UserController;
import controller.PastebinController;
import controller.TagController;
import model.dao.DaoUtil;
import model.domain.Entity;
import model.domain.User;

import java.util.List;

/**
 * The class is a simulation of Spring framework RequestMapping for mapping the front-end request to the corresponding controllers
 * @author Rachel Yang
 */
public class RequestMapping {

  /*
  This attribute is used for storing the current logged in session user.
   */
  private User user;

  /*
  Controllers need to be mapped
   */
  private UserController userController;
  private TagController tagController;
  private ArticleController articleController;
  private PastebinController pastebinController;

  private DaoUtil daoUtil;

  /**
   *
   * @param guardianAPIStrategy The GuardianAPI fetching strategy to be injected into the construction of RequestMapping
   * @param pastebinAPIStrategy The PastebinAPI fetching strategy to be injected into the construction of RequestMapping
   * @param daoUtil
   */
  public RequestMapping(
      GuardianAPIStrategy guardianAPIStrategy,
      PastebinAPIStrategy pastebinAPIStrategy,
      DaoUtil daoUtil) {
    /*
    Both strategies are injected in RequestMapping object, they can be either online or offline
     */
    this.userController = new UserController(guardianAPIStrategy, daoUtil);
    this.tagController = new TagController(guardianAPIStrategy, daoUtil);
    this.articleController = new ArticleController(guardianAPIStrategy, daoUtil);
    this.pastebinController = new PastebinController(pastebinAPIStrategy);

    this.daoUtil = daoUtil;
  }

  /**
   * The method is for routing the login request from the front-end to the UserController
   * @param token The token for authorization to login to the API
   * @return The Entity which stores the response info
   */
  public Entity login(String token) {
    Entity returnedEntity = userController.login(token);
    if (returnedEntity.getEntityType().equals("User")) {
      this.user = (User) returnedEntity;
      this.user.setToken(token);
    }
    return returnedEntity;
  }

  /**
   * The method is for routing the search All Tags By Keyword request from the front-end to the TagController
   * @param token The token for authorization to search tags from the API
   * @param keyword The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchAllTagsByKeyword(String token, String keyword) {
    return tagController.searchAllTagsByKeyword(token, keyword);
  }

  /**
   * The method is for routing the search All Tags By Keyword request from the front-end to the TagController
   * @param token The token for authorization to search tags from the API
   * @param keyword The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchOnePageTagsByKeyword(String token, String keyword) {
    return tagController.searchOnePageTagsByKeyword(token, keyword);
  }

  /**
   * The method is for routing the search All Articles By Tag request from the front-end to the ArticleController
   * @param token The token for authorization to search tags from the API
   * @param tag The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchAllArticlesByTag(String token, String tag) {
    return articleController.searchAllArticlesByTag(token, tag);
  }

  /**
   * The method is for routing the search All Articles By Tag request from the front-end to the ArticleController
   * @param token The token for authorization to search tags from the API
   * @param tag The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchOnePageArticlesByTag(String token, String tag) {
    return articleController.searchOnePageArticlesByTag(token, tag);
  }

  /**
   * The method is for routing the search cached Tags By Keyword request from the front-end to the TagController
   * @param keyword The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchCachedTagsByKeyword(String token, String keyword) {
    return tagController.searchCachedTagsByKeyword(token, keyword);
  }

  /**
   * The method is for routing the search All cached Articles By Tag request from the front-end to the ArticleController
   * @param token The token for authorization to search articles from the database
   * @param tag The tag which will be matched for searching articles
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchCachedArticleByTag(String token, String tag) {
    return articleController.searchCachedArticleByTag(token, tag);
  }

  /**
   * The method is for routing the get pastebin request from the front-end to the PastebinController
   * @param token The token for authorization to get the Pastebin link from the API
   * @param copiedText The text content to be copy and pasted in the Pastebin link
   * @return The Entity Pastebin stores the valid pastebin link and other info
   */
  public Entity getPastebinLink(String token, String copiedText) {
    return pastebinController.getPastebinLink(token, copiedText);
  }

  /**
   * The method is for user log out and set corresponding status for the current user
   */
  public void userLogOut() {
    if (this.user != null) {
      this.user.setLoginStatus(false);
    }
  }

  /**
   * Getter method for getting current user
   * @return The current user
   */
  public User getUser() {
    return this.user;
  }

  /**
   * Getter method for getting DaoUtil object
   * @return The DaoUtil object
   */
  public DaoUtil getDaoUtil() {
    return daoUtil;
  }
}
