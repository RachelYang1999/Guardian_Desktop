package util;

import controller.LoginController;
import model.domain.Entity;
import model.domain.User;

public class RequestMapping {
    /*
    The mode can only be online or offline
     */
    private String mode;

    /*
    This attribute is used for storing the current logged in session user.
     */
    private User user;

    /*
    Controllers need to be mapped
     */
    private LoginController userController;


    public RequestMapping(GuardianAPIStrategy guardianAPIStrategy) {
        this.mode = mode;
        this.userController = new LoginController(guardianAPIStrategy);
    }

    public Entity login(String token) {
        Entity returnedEntity = userController.login(token);
        if (returnedEntity.getEntityType().equals("User")) {
            this.user = (User) returnedEntity;
            this.user.setToken(token);
        }
        return userController.login(token);
    }

    public void userLogOut() {
        if (this.user != null) {
            this.user.setLoginStatus(false);
        }
    }

}
