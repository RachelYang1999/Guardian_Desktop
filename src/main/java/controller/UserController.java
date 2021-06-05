package controller;

import model.dao.DaoUtil;
import model.dao.UserDao;
import model.domain.Entity;
import model.service.UserService;
import util.GuardianAPIStrategy;

/**
 * The Controller of the Entity User in MVC pattern
 * @author Rachel Yang
 */
public class UserController {

  private UserService userService;

  /**
   * The constructor of TagController
   * @param guardianAPIStrategy The API fetching strategy to be injected into the construction of UserController
   * @param daoUtil The Database Access Object Util to be injected into the construction of UserController
   */
  public UserController(GuardianAPIStrategy guardianAPIStrategy, DaoUtil daoUtil) {
    this.userService = new UserService(guardianAPIStrategy, new UserDao(daoUtil));
  }

  /**
   * The method is for calling login method of UserService which encapsulated the business logic of login function
   * @param token The token for authorization to login to the API
   * @return The Entity which stores the response info
   */
  public Entity login(String token) {
    return userService.login(token);
  }
}
