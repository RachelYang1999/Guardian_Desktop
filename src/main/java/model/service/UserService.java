package model.service;

import factory.entityfactory.DefaultErrorInfoFactory;
import factory.entityfactory.EntityFactory;
import factory.entityfactory.ErrorInfoFactory;
import factory.entityfactory.LoginUserFactory;
import model.dao.AbstractDao;
import model.dao.DaoUtil;
import model.dao.UserDao;
import model.domain.Entity;
import model.domain.User;
import org.json.JSONObject;
import util.GuardianAPIStrategy;

import java.util.List;
import static java.util.Arrays.asList;


/**
 * The Model Service of the Entity User which encapsulated the business logic for searching articles by input tag from the API
 * @author Rachel Yang
 */
public class UserService {
  private EntityFactory entityFactory;
  private EntityFactory defaultErrorFactory;
  private User user;
  private GuardianAPIStrategy guardianAPIStrategy;

  private AbstractDao userDao;

  /**
   * The constructor of TagService
   * @param guardianAPIStrategy The API fetching strategy to be injected into the construction of UserService
   * @param userDao The User Database Access Object to be injected into the construction of ArticleService,
   *                   which will be used for CURD operation between API response and the database
   */
  public UserService(GuardianAPIStrategy guardianAPIStrategy, UserDao userDao) {
    this.guardianAPIStrategy = guardianAPIStrategy;
    this.defaultErrorFactory = new DefaultErrorInfoFactory();
    this.userDao = userDao;
  }

  /**
   * This method is for log in
   * @param token The token for authorization to login from the API
   * @return The entity which store the response information
   */
  public Entity login(String token) {
    JSONObject responseJSON = guardianAPIStrategy.login(token);
    if (responseJSON == null) {
      System.out.println("[UserService] login responseJSON is null: " + responseJSON.toString());
    }
    Entity returnEntity = defaultErrorFactory.createEntity(responseJSON);
    System.out.println("[UserService] login response: " + responseJSON.toString());

    if (responseJSON.has("response")) {
      if (responseJSON.getJSONObject("response").has("status")) {
        if (responseJSON.getJSONObject("response").getString("status").equals("ok")) {
          entityFactory = new LoginUserFactory();
          returnEntity = entityFactory.createEntity(responseJSON);
          this.user = (User) returnEntity;
          this.user.setToken(token);
        }
      }
    } else if (responseJSON.has("message")) {
      entityFactory = new ErrorInfoFactory();
      returnEntity = entityFactory.createEntity(responseJSON);
    }

    // Default object is ErrorInfo entity
    return returnEntity;
  }

  public Entity updateUserWithInputInteger(User currentUser, String inputInt) {
    Entity returnEntity = currentUser;
    return returnEntity;
  }
}
