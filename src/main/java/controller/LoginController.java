package controller;

import model.dao.DaoUtil;
import model.dao.UserDao;
import model.domain.Entity;
import model.service.UserService;
import util.GuardianAPIStrategy;

public class LoginController {

    private UserService userService;

    public LoginController(GuardianAPIStrategy guardianAPIStrategy, DaoUtil daoUtil) {
        this.userService = new UserService(guardianAPIStrategy, new UserDao(daoUtil));
    }

    public Entity login(String token) {
        return userService.login(token);
    }

}
