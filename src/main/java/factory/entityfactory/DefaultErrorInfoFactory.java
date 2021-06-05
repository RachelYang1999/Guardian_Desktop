package factory.entityfactory;

import model.domain.Entity;
import model.domain.ErrorInfo;
import org.json.JSONObject;

/**
 * The Default Error Info factory is for producing ErrorInfo object with default information
 * @author Rachel Yang
 */
public class DefaultErrorInfoFactory implements EntityFactory {

  /**
   * Create default ErrorInfo object
   * @param response The response from API or dummy API in the JSON format
   * @return ErrorInfo object with default information
   */
  @Override
  public Entity createEntity(JSONObject response) {
    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setMessage("Unknown Error!");
    return errorInfo;
  }
}
