package controller;

import model.domain.Entity;
import model.service.UserService;
import util.GuardianAPIStrategy;

public class LoginController {

    private UserService userService;

    public LoginController(GuardianAPIStrategy guardianAPIStrategy) {
        this.userService = new UserService(guardianAPIStrategy);
    }

    public Entity login(String token) {
        return userService.login(token);
    }

}
