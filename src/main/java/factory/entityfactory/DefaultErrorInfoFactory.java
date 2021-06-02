package factory.entityfactory;

import model.domain.Entity;
import model.domain.ErrorInfo;
import org.json.JSONObject;

public class DefaultErrorInfoFactory implements EntityFactory {

  @Override
  public Entity createEntity(JSONObject response) {

    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setMessage("Unknown Error!");

    return errorInfo;
  }
}
