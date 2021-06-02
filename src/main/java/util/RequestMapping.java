package util;

import controller.ArticleController;
import controller.LoginController;
import controller.PastebinController;
import model.dao.DaoUtil;
import model.domain.Entity;
import model.domain.User;

import java.sql.Connection;
import java.util.List;

public class RequestMapping {



    /*
    This attribute is used for storing the current logged in session user.
     */
    private User user;

    /*
    Controllers need to be mapped
     */
    private LoginController userController;
    private ArticleController articleController;
    private PastebinController pastebinController;

    private DaoUtil daoUtil;


    public RequestMapping(GuardianAPIStrategy guardianAPIStrategy, PastebinAPIStrategy pastebinAPIStrategy, DaoUtil daoUtil) {
        /*
        Both strategies are injected in RequestMapping object, they can be either online or offline
         */
        this.userController = new LoginController(guardianAPIStrategy, daoUtil);
        this.articleController = new ArticleController(guardianAPIStrategy, daoUtil);
        this.pastebinController = new PastebinController(pastebinAPIStrategy);

        this.daoUtil = daoUtil;

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
        return articleController.searchByTag(token, tag);
    }

    public List<Entity> searchByCachedTag(String token, String tag) {
        return articleController.searchByCachedTag(token, tag);
    }

    public Entity getPastebinLink(String token, String copiedText) {
        return pastebinController.getPastebinLink(token, copiedText);
    }



    public void userLogOut() {
        if (this.user != null) {
            this.user.setLoginStatus(false);
        }
    }

    public User getUser() {
        return this.user;
    }

}
