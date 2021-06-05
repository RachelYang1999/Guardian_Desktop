package controller;

import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.dao.TagDao;
import model.domain.Entity;
import model.service.TagService;
import util.GuardianAPIStrategy;

import java.util.List;

/**
 * The Controller of the Entity Tag with the use of MVC pattern
 * @author Rachel Yang
 */
public class TagController {

  private TagService tagService;

  /**
   * The constructor of TagController
   * @param guardianAPIStrategy The API fetching strategy to be injected into the construction of TagController
   * @param daoUtil The Database Access Object Util to be injected into the construction of TagController, which will be used in the TagService
   */
  public TagController(GuardianAPIStrategy guardianAPIStrategy, DaoUtil daoUtil) {
    this.tagService = new TagService(guardianAPIStrategy, new TagDao(daoUtil), new ArticleDao(daoUtil));
  }

  /**
   * The method is for calling searchAllTagsByKeyword method which encapsulated the business logic for searching tags by input keyword
   * @param token The token for authorization to search tags from the API
   * @param keyword The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchAllTagsByKeyword(String token, String keyword) {
    return tagService.searchAllTagsByKeyword(token, keyword);
  }

  /**
   * The method is for calling searchAllTagsByKeyword method which encapsulated the business logic for searching tags by input keyword
   * @param keyword The keyword for searching matching tags
   * @return The list of Entity which stores the response info
   */
  public List<Entity> searchCachedTagsByKeyword(String token, String keyword) {
    return tagService.searchCachedTagsByKeyword(keyword);
  }
}
