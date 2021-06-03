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

public class TagService {

  private EntityCollectionFactory entityCollectionFactory;
  private EntityFactory defaultErrorFactory;
  private EntityFactory entityFactory;
  private User user;
  private GuardianAPIStrategy guardianAPIStrategy;

  private AbstractDao tagDao;
  private AbstractDao articleDao;


  public TagService(GuardianAPIStrategy guardianAPIStrategy, AbstractDao tagDao, AbstractDao articleDao) {
    this.guardianAPIStrategy = guardianAPIStrategy;
    this.defaultErrorFactory = new DefaultErrorInfoFactory();
    this.entityCollectionFactory = new ArticleFactory();
    this.tagDao = tagDao;
    this.articleDao = articleDao;
  }

  public List<Entity> searchAllTagsByKeyword(String token, String keyword) {
    List<Entity> entities = new ArrayList<>();

    return entities;
  }

  public List<Entity> searchTagsByKeyword(String token, String keyword, int pageNumber) {
    List<Entity> entities = new ArrayList<>();

    return entities;
  }

  public List<Entity> searchCachedTagsByKeyword(String keyword) {
    List<Entity> entities = new ArrayList<>();

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
