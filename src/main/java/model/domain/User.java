package model.domain;

public class User implements Entity {
    private String token;
    private boolean loginStatus;
    private String userTier;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserTier() {
        return userTier;
    }

    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    @Override
    public String getEntityInformation() {
        String status = "";
        if (loginStatus) {
            status = "Logged In";
        } else {
            status = "Not Logged In";
        }

        String result =
//                "Token: " + token + "\n" +
                "User Tier: " + userTier + "\n" +
                "Login Status: " + status + "\n";
        return result;
    }

    @Override
    public String getEntityType() {
        return "User";
    }
}
