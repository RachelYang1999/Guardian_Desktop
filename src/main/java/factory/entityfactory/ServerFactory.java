package factory.entityfactory;

import model.Entity;
import model.Server;
import org.json.JSONObject;

import java.util.List;

public class ServerFactory implements EntityFactory {
    @Override
    public Entity createEntity(String response) {
        if (response.equals("")) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(response);
        String status = jsonObject.getString("status");
        System.out.println("[ServerFactory] status variable: " + status);

        Server server = new Server();
        server.setStatus(status);

        return server;
    }

    @Override
    public List<Entity> createEntities(String response) {
        return null;
    }
}
