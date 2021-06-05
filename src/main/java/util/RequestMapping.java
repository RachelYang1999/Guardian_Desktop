package util;

import controller.ArticleController;
import controller.LoginController;
import controller.PastebinController;
import controller.TagController;
import model.dao.DaoUtil;
import model.domain.Entity;
import model.domain.User;

import java.util.List;

public class RequestMapping {

  /*
  This attribute is used for storing the current logged in session user.
   */
  private User user;

  /*
  Controllers need to be mapped
   */
  private LoginController userController;
  private TagController tagController;
  private ArticleController articleController;
  private PastebinController pastebinController;

  private DaoUtil daoUtil;

  public RequestMapping(
      GuardianAPIStrategy guardianAPIStrategy,
      PastebinAPIStrategy pastebinAPIStrategy,
      DaoUtil daoUtil) {
    /*
    Both strategies are injected in RequestMapping object, they can be either online or offline
     */
    this.userController = new LoginController(guardianAPIStrategy, daoUtil);
    this.tagController = new TagController(guardianAPIStrategy, daoUtil);
    this.articleController = new ArticleController(guardianAPIStrategy, daoUtil);
    this.pastebinController = new PastebinController(pastebinAPIStrategy);

    this.daoUtil = daoUtil;
  }

  public Entity login(String token) {
    Entity returnedEntity = userController.login(token);
    if (returnedEntity.getEntityType().equals("User")) {
      this.user = (User) returnedEntity;
      this.user.setToken(token);
    }
    return returnedEntity;
  }

  public List<Entity> searchAllTagsByKeyword(String token, String keyword) {
    return tagController.searchAllTagsByKeyword(token, keyword);
  }

  public List<Entity> searchAllArticlesByTag(String token, String tag) {
    return articleController.searchAllArticlesByTag(token, tag);
  }
//
  public List<Entity> searchCachedTagsByKeyword(String token, String keyword) {
    return tagController.searchCachedTagsByKeyword(token, keyword);
  }

  public List<Entity> searchCachedArticleByTag(String token, String tag) {
    return articleController.searchCachedArticleByTag(token, tag);
  }

  public Entity getPastebinLink(String token, String copiedText) {
    return pastebinController.getPastebinLink(token, copiedText);
  }

  public void userLogOut() {
    if (this.user != null) {
      this.user.setLoginStatus(false);
    }
  }

  public User getUser() {
    return this.user;
  }

  public static void main(String[] args) {
    System.out.println(new RequestMapping(
            new GuardianOnlineAPIStrategy(),
            new PastebinOfflineAPIStrategy(),
            new DaoUtil()
    ).searchAllArticlesByTag("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "books/roxane-gay").size());
  }

  public DaoUtil getDaoUtil() {
    return daoUtil;
  }
}
