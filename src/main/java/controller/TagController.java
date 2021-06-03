package controller;

import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.dao.TagDao;
import model.domain.Entity;
import model.service.TagService;
import util.GuardianAPIStrategy;

import java.util.List;

public class TagController {

  private TagService tagService;

  public TagController(GuardianAPIStrategy guardianAPIStrategy, DaoUtil daoUtil) {
    this.tagService = new TagService(guardianAPIStrategy, new TagDao(daoUtil), new ArticleDao(daoUtil));
  }

  public List<Entity> searchAllTagsByKeyword(String token, String keyword) {
    return tagService.searchAllTagsByKeyword(token, keyword);
  }

  public List<Entity> searchCachedTagsByKeyword(String token, String tag) {
    return tagService.searchCachedTagsByKeyword(tag);
  }
}
