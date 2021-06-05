package controller;

import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.domain.Entity;
import model.service.ArticleService;
import util.GuardianAPIStrategy;

import java.util.List;

/**
 * The Controller of the Entity Article with the use of MVC pattern
 * @author Rachel Yang
 */
public class ArticleController {

  private ArticleService articleService;

  /**
   * The constructor of ArticleController
   * @param guardianAPIStrategy The API fetching strategy to be injected into the construction of ArticleController
   * @param daoUtil The Database Access Object Util to be injected into the construction of ArticleController, which will be used in the ArticleService
   */
  public ArticleController(GuardianAPIStrategy guardianAPIStrategy, DaoUtil daoUtil) {
    this.articleService = new ArticleService(guardianAPIStrategy, new ArticleDao(daoUtil));
  }

  /**
   * The method is for calling searchAllArticlesByTag method which encapsulated the business logic for searching articles by input tag from the API
   * @param token The token for authorization to search articles from the API
   * @param tag The tag which will be matched for searching articles
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchAllArticlesByTag(String token, String tag) {
    return articleService.searchAllArticlesByTag(token, tag);
  }

  /**
   * The method is for calling searchCachedArticleByTag method which encapsulated the business logic for searching articles by input tag from the database
   * @param token The token for authorization to search articles from the database
   * @param tag The tag which will be matched for searching articles
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchCachedArticleByTag(String token, String tag) {
    return articleService.searchCachedArticleByTag(tag);
  }
}
