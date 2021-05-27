package util;

import controller.ArticleController;
import controller.LoginController;
import model.domain.Entity;
import model.domain.User;

import java.util.List;

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
    private ArticleController articleController;


    public RequestMapping(GuardianAPIStrategy guardianAPIStrategy) {
        this.mode = mode;
        this.userController = new LoginController(guardianAPIStrategy);
        this.articleController = new ArticleController(guardianAPIStrategy);

    }

    public Entity login(String token) {
        Entity returnedEntity = userController.login(token);
        if (returnedEntity.getEntityType().equals("User")) {
            this.user = (User) returnedEntity;
            this.user.setToken(token);
        }
        return returnedEntity;
    }

    public List<Entity> searchByTag(String token, String tag) {
        List<Entity> entities = articleController.searchByTag(token, tag);
        return entities;
    }

    public void userLogOut() {
        if (this.user != null) {
            this.user.setLoginStatus(false);
        }
    }

}
