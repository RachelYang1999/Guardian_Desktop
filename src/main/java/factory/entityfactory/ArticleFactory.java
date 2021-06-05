package factory.entityfactory;

import model.domain.Article;
import model.domain.ArticleData;
import model.domain.Entity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * The ArticleData list factory for producing Article objects which store article information of a response
 * @author Rachel Yang
 */
public class ArticleFactory implements EntityCollectionFactory {

  /**
   * Get values of the JSONObject by the key and check if it exists
   * @param response The response from the API or dummy API
   * @param field The key value
   * @return
   */
  public String checkAndGetString(JSONObject response, String field) {
    if (response.has(field)) {
      return response.getString(field);
    } else {
      return "N/A";
    }
  }

  /**
   * This method is for create corresponding Article entity which stores article information of the API response
   * @param response The response from API or dummy API in the JSON format
   * @return Article entities which stores information of the API response
   */
  public Entity createEntity(JSONObject response) {
    Article article = new Article();

    List<Entity> articleDataListEntityType = new ArticleDataFactory().createEntities(response);
    article.setArticleDataList(articleDataListEntityType);
    article.setWebTitle(checkAndGetString(response, "webTitle"));
    article.setId(checkAndGetString(response, "id"));
    return article;
  }

  /**
   * This method is for create corresponding Article entities which store article information of the API response
   * @param response The response from API or dummy API in the JSON format
   * @return List of Article entities which store information of the API response
   */
  @Override
  public List<Entity> createEntities(JSONObject response) {
    List<Entity> result = new ArrayList<>();

    JSONArray resultArray = response.getJSONObject("response").getJSONArray("results");
    //
    // System.out.println("------------------------------------------------------------------------");
    //        System.out.println("ArticleFactory createEntities resultArray");
    //        System.out.println(resultArray.toString());

    for (int i = 0; i < resultArray.length(); i++) {
      JSONObject currentResultJsonObject = resultArray.getJSONObject(i);
      //
      // System.out.println("------------------------------------------------------------------------");
      //            System.out.println("ArticleFactory createEntities loop");
      //            System.out.println("currentResultJsonObject: " +
      // currentResultJsonObject.toString());

      Entity createdEntity = new ArticleFactory().createEntity(currentResultJsonObject);
      result.add(createdEntity);
    }
    return result;
  }
}
