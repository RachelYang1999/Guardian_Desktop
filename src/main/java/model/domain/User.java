package model.domain;

public class User implements Entity {
  private String token;
  private boolean loginStatus;
  private String userTier;
  private String inputInt;

  /**
   * Getter method for getting User token
   * @return User token
   */
  public String getToken() {
    return token;
  }

  /**
   * Setter method for setting User token
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Getter method for getting User loginStatus
   * @return User loginStatus
   */
  public boolean isLoginStatus() {
    return loginStatus;
  }

  /**
   * Setter method for setting User loginStatus
   */
  public void setLoginStatus(boolean loginStatus) {
    this.loginStatus = loginStatus;
  }

  /**
   * Getter method for getting User userTier
   * @return User userTier
   */
  public String getUserTier() {
    return userTier;
  }

  /**
   * Setter method for setting User userTier
   */
  public void setUserTier(String userTier) {
    this.userTier = userTier;
  }

  /**
   * Getter method for getting User input int
   * @return User input int
   */
  public String getInputInt() {
    return "";
  }

  /**
   * Setter method for setting User input int
   */
  public void setInputInt(String inputInt) {

  }

  /**
   * Get the information about User
   * @return Information about User
   */
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
        "User Tier: " + userTier + "\n" + "Login Status: " + status + "\n";
    return result;
  }

  /**
   * Get the User entity type
   * @return User
   */
  @Override
  public String getEntityType() {
    return "User";
  }
}
