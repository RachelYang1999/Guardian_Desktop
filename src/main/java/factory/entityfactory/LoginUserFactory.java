package factory.entityfactory;

import model.domain.Entity;
import model.domain.User;
import org.json.JSONObject;

/**
 * The User factory is for create logged in users object which stores the user information from the API or dummy API response
 * @author Rachel Yang
 */
public class LoginUserFactory implements EntityFactory {

  /**
   *
   * @param response The response from API or dummy API in the JSON format
   * @return The User object which stores the user information from the API or dummy API response
   */
  @Override
  public Entity createEntity(JSONObject response) {
    User user = new User();
    user.setUserTier(response.getJSONObject("response").getString("userTier"));
    user.setLoginStatus(true);
    return user;
  }
}
