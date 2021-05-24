package controller;

import model.domain.Entity;
import model.service.UserService;

public class LoginController {

    private UserService userService;
    private String mode;

    public LoginController(String mode) {
        this.mode = mode;
        this.userService = new UserService(mode);
    }

    public Entity login(String token) {
        return userService.login(token);
    }

}
