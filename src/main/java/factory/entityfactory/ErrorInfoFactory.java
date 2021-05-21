package factory.entityfactory;

import model.Entity;
import model.ErrorInfo;
import org.json.JSONObject;

import java.util.List;

public class ErrorInfoFactory implements EntityFactory {

    @Override
    public Entity createEntity(String response) {

        JSONObject jsonObject = new JSONObject(response);

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setMessage(jsonObject.getString("message"));
        errorInfo.setErrorCode(jsonObject.getInt("code"));

        return errorInfo;
    }

    @Override
    public List<Entity> createEntities(String response) {
        return null;
    }
}
