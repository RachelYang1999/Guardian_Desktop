package factory.entityfactory;

import model.domain.ArticleData;
import model.domain.Entity;
import model.domain.TagData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagDataFactory implements EntityCollectionFactory {

  public String checkAndGetString(JSONObject response, String field) {
    if (response.has(field)) {
      try {
        return response.getString(field);
      } catch (Exception e) {
//        System.out.println("TagDataFactory Get string wrong");
        //                e.printStackTrace();
      }
      try {
        JSONArray jsonArray = response.getJSONArray(field);
        return getContentFromJsonArray(jsonArray);
      } catch (Exception e) {
//        System.out.println("TagDataFactory Get string wrong");
      }

      try {
        return response.get(field).toString();
      } catch (Exception e) {
//        System.out.println("TagDataFactory Get string wrong");
      }
      return "N/A";
    } else {
      return "N/A";
    }
  }

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
        System.out.println("[ArticleDataFactory] getContentFromJsonArray exception?");
      }
    }
    return result;
  }

  @Override
  public List<Entity> createEntities(JSONObject response) {
    List<Entity> result = new ArrayList<>();

    for (Object key : response.keySet()) {

      String keyString = (String) key;
      String valueString = checkAndGetString(response, keyString);

      TagData tagData = new TagData();
      tagData.setDataAttribute(keyString);
      tagData.setDataContent(valueString);

      result.add(tagData);
    }

    return result;
  }

      public static void main(String[] args) {
          String s =
   "{\"id\":\"travel/gay-and-lesbian-travel\",\"type\":\"keyword\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webTitle\":\"Gay and lesbian travel\",\"webUrl\":\"https://www.theguardian.com/travel/gay-and-lesbian-travel\",\"apiUrl\":\"https://content.guardianapis.com/travel/gay-and-lesbian-travel\"}";
          JSONObject object = new JSONObject(s);
          for (Entity e : new TagDataFactory().createEntities(object)) {
              System.out.println(e.getEntityInformation());
          }
      }
}
