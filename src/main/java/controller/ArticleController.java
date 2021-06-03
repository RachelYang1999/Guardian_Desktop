package controller;

import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.domain.Entity;
import model.service.ArticleService;
import util.GuardianAPIStrategy;

import java.util.List;

public class ArticleController {

  private ArticleService articleService;

  public ArticleController(GuardianAPIStrategy guardianAPIStrategy, DaoUtil daoUtil) {
    this.articleService = new ArticleService(guardianAPIStrategy, new ArticleDao(daoUtil));
  }

  public List<Entity> searchAllArticlesByTag(String token, String tag) {
    return articleService.searchAllArticlesByTag(token, tag);
  }

  public List<Entity> searchCachedArticleByTag(String token, String tag) {
    return articleService.searchCachedArticleByTag(tag);
  }
}
