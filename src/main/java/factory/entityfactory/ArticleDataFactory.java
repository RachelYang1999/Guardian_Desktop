package factory.entityfactory;

import model.domain.ArticleData;
import model.domain.Entity;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleDataFactory implements EntityCollectionFactory {
    public String checkAndGetString(JSONObject response, String field) {
        if (response.has(field)) {
            return response.getString(field);
        } else {
            return "N/A";
        }
    }

    public Entity createEntity(JSONObject response) {
        return null;
    }

    @Override
    public List<Entity> createEntities(JSONObject response) {
        List<Entity> result = new ArrayList<>();

        for (Object key : response.keySet()) {

            String keyString = (String) key;
            String valueString = checkAndGetString(response, keyString);

            ArticleData articleData = new ArticleData();
            articleData.setDataAttribute(keyString);
            articleData.setDataContent(valueString);

            result.add(articleData);
        }

        return result;
    }

//    public static void main(String[] args) {
//        String s = "{\"id\":\"travel/gay-and-lesbian-travel\",\"type\":\"keyword\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webTitle\":\"Gay and lesbian travel\",\"webUrl\":\"https://www.theguardian.com/travel/gay-and-lesbian-travel\",\"apiUrl\":\"https://content.guardianapis.com/travel/gay-and-lesbian-travel\"}";
//        JSONObject object = new JSONObject(s);
//        for (Entity e : new ArticleDataFactory().createEntities(object)) {
//            System.out.println(e.getEntityInformation());
//        }
//    }
}
