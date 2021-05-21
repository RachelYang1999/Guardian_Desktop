package factory.entityfactory;

import model.Entity;
import model.ErrorInfo;
import org.json.JSONObject;

public class DefaultErrorInfoFactory implements EntityFactory {

    @Override
    public Entity createEntity(JSONObject response) {

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("Unknown Error!");

        return errorInfo;
    }

}
