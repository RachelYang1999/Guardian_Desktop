package factory.entityfactory;

import model.domain.Entity;
import model.domain.Tag;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Tag factory for producing Tag objects which store tag information of a response
 * @author Rachel Yang
 */
public class TagFactory implements EntityCollectionFactory {
  public String checkAndGetString(JSONObject response, String field) {
    if (response.has(field)) {
      return response.getString(field);
    } else {
      return "N/A";
    }
  }

  /**
   * This method is for create corresponding Tag entity which stores tag information of the API response
   * @param response The response from API or dummy API in the JSON format
   * @return Tag entities which stores information of the API response
   */
  public Entity createEntity(JSONObject response) {
    Tag tag = new Tag();

    List<Entity> tagDataListEntityType = new TagDataFactory().createEntities(response);
    tag.setTagName(checkAndGetString(response, "id"));
    tag.setTagDataList(tagDataListEntityType);
    return tag;
  }

  /**
   * This method is for create Tag entities which store tag information of the API response
   * @param response The response from API or dummy API in the JSON format
   * @return List of Article entities which store information of the API response
   */
  @Override
  public List<Entity> createEntities(JSONObject response) {
    List<Entity> result = new ArrayList<>();

    JSONArray resultArray = response.getJSONObject("response").getJSONArray("results");
    for (int i = 0; i < resultArray.length(); i++) {
      JSONObject currentResultJsonObject = resultArray.getJSONObject(i);
      Entity createdEntity = new TagFactory().createEntity(currentResultJsonObject);
      result.add(createdEntity);
    }
    return result;
  }
}
