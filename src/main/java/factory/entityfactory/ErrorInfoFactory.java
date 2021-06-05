package factory.entityfactory;

import model.domain.Entity;
import model.domain.ErrorInfo;
import org.json.JSONObject;

/**
 * The ErrorInfo factory is for producing ErrorInfo object which stores the error message from the API or dummy API response
 * @author Rachel Yang
 */
public class ErrorInfoFactory implements EntityFactory {

  /**
   *
   * @param response The response from API or dummy API in the JSON format
   * @return The ErrorInfo object which stores the error message from the API or dummy API response
   */
  @Override
  public Entity createEntity(JSONObject response) {
    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setMessage(response.getString("message"));
    return errorInfo;
  }
}
