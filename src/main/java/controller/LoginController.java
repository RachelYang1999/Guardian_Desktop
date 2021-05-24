package controller;

import model.domain.Entity;
import model.service.UserService;
import model.service.UserServiceOffline;
import model.service.UserServiceOnline;

public class LoginController {

    private UserService userService;
    private String mode;

    public LoginController(String mode) {
        this.mode = mode;
        if (mode.equals("online")) {
            this.userService = new UserServiceOnline();
        } else if (mode.equals("offline")) {
            this.userService = new UserServiceOffline();
        }

    }

    public Entity login(String token) {
        return userService.login(token);
    }

}
