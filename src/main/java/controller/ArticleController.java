package controller;

import model.domain.Entity;
import model.service.ArticleService;
import model.service.UserService;
import util.GuardianAPIStrategy;

import java.util.List;

public class ArticleController {

    private ArticleService articleService;

    public ArticleController(GuardianAPIStrategy guardianAPIStrategy) {
        this.articleService = new ArticleService(guardianAPIStrategy);
    }

    public List<Entity> searchByTag(String token, String tag) {
        return articleService.getAllArticles(token, tag);
    }

}
