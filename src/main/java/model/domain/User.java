package model.domain;

public class User implements Entity {
    private String token;
    private boolean loginStatus;
    private String userTier;

    public String getToken() {
        return null;
    }

    public void setToken(String token) {

    }

    public boolean isLoginStatus() {
        return false;
    }

    public void setLoginStatus(boolean loginStatus) {

    }

    public String getUserTier() {
        return null;
    }

    public void setUserTier(String userTier) {

    }

    @Override
    public String getEntityInformation() {
        return null;
    }

    @Override
    public String getEntityType() {
        return "Not User";
    }
}
