package factory.entityfactory;

import model.domain.Entity;
import model.domain.ErrorInfo;
import org.json.JSONObject;

public class ErrorInfoFactory implements EntityFactory {

    @Override
    public Entity createEntity(JSONObject response) {

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(response.getString("message"));

        return errorInfo;
    }

}
