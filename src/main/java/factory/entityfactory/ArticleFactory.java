package factory.entityfactory;

import model.domain.Article;
import model.domain.ArticleData;
import model.domain.Entity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ArticleFactory implements EntityCollectionFactory {
    public String checkAndGetString(JSONObject response, String field) {
        if (response.has(field)) {
            return response.getString(field);
        } else {
            return "N/A";
        }
    }


    public Entity createEntity(JSONObject response) {
        Article article = new Article();

        List<Entity> articleDataListEntityType = new ArticleDataFactory().createEntities(response);
        article.setArticleDataList(articleDataListEntityType);
        article.setWebTitle(checkAndGetString(response,"webTitle"));
        article.setId(checkAndGetString(response,"id"));
        return article;
    }

    @Override
    public List<Entity> createEntities(JSONObject response) {
        List<Entity> result = new ArrayList<>();

        JSONArray resultArray = response.getJSONObject("response").getJSONArray("results");
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("ArticleFactory createEntities resultArray");
//        System.out.println(resultArray.toString());

        for (int i = 0; i < resultArray.length(); i ++) {
            JSONObject currentResultJsonObject = resultArray.getJSONObject(i);
//            System.out.println("------------------------------------------------------------------------");
//            System.out.println("ArticleFactory createEntities loop");
//            System.out.println("currentResultJsonObject: " + currentResultJsonObject.toString());

            Entity createdEntity = new ArticleFactory().createEntity(currentResultJsonObject);
            result.add(createdEntity);
        }
        return result;
    }
}
