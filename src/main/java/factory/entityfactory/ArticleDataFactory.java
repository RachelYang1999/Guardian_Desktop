package factory.entityfactory;

import model.domain.ArticleData;
import model.domain.Entity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The ArticleData list factory for producing ArticleData objects which store information of a response
 * @author Rachel Yang
 */
public class ArticleDataFactory implements EntityCollectionFactory {

  /**
   * Get values of the JSONObject by the key
   * @param response The response from the API or dummy API
   * @param field The key value
   * @return
   */
  public String checkAndGetString(JSONObject response, String field) {
    if (response.has(field)) {
      try {
        return response.getString(field);
      } catch (Exception e) {
//        System.out.println("ArticleDataFactory Get string wrong");
        //                e.printStackTrace();
      }
      try {
        JSONArray jsonArray = response.getJSONArray(field);
        return getContentFromJsonArray(jsonArray);
      } catch (Exception e) {
//        System.out.println("ArticleDataFactory Get string wrong");
      }

      try {
        return response.get(field).toString();
      } catch (Exception e) {
//        System.out.println("ArticleDataFactory Get string wrong");
      }
      return "N/A";
    } else {
      return "N/A";
    }
  }

  /**
   *
   * This method is for getting contents of a JSONArray in self-defined format
   * @param jsonArray The JSONArray object to get contents from
   * @return The content in the self-defined format
   */
  public String getContentFromJsonArray(JSONArray jsonArray) {
    String result = "\n";
    for (int i = 0; i < jsonArray.length(); i++) {
      try {
        JSONObject objects = jsonArray.getJSONObject(i);
        Iterator keys = objects.keys();
        while (keys.hasNext()) {
          String key = keys.next().toString();
          String value = checkAndGetString(objects, key);
          result += "\t\t" + key + ": " + value + "\n";
          System.out.println("\t\t" + key + ": " + value + "\n");
        }
      } catch (Exception e) {
        e.printStackTrace();
//        System.out.println("[ArticleDataFactory] getContentFromJsonArray exception?");
      }
    }
    return result;
  }

  /**
   * This method is for create ArticleData objects which store information of the API response
   * @param response The response from API or dummy API in the JSON format
   * @return List of entities which store information of the API response
   */
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

}
