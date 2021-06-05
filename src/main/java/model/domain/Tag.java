package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Tag implements Entity {

  private String tagName;
  private List<Entity> tagDataList = new ArrayList<>();
  private String info;

  private String relatedKeyword;

  /**
   * Getter method for getting Tag name
   * @return Tag name
   */
  public String getTagName() {
    return tagName;
  }

  /**
   * Setter method for setting Tag name
   */
  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  /**
   * Getter method for getting Tag data list
   * @return Tag data list
   */
  public List<Entity> getTagDataList() {
    return tagDataList;
  }

  /**
   * Setter method for setting Tag data list
   */
  public void setTagDataList(List<Entity> tagDataList) {
    this.tagDataList = tagDataList;
  }

  /**
   * Getter method for getting Tag info
   * @return Tag info
   */
  public String getInfo() {
    return info;
  }

  /**
   * Setter method for setting Tag info
   */
  public void setInfo(String info) {
    this.info = info;
  }

  /**
   * Getter method for getting Tag relatedKeyword
   * @return Tag relatedKeyword
   */
  public String getRelatedKeyword() {
    return relatedKeyword;
  }

  /**
   * Setter method for setting Tag relatedKeyword
   */
  public void setRelatedKeyword(String relatedKeyword) {
    this.relatedKeyword = relatedKeyword;
  }

  /**
   * Get the information about Tag
   * @return Information about Tag
   */
  @Override
  public String getEntityInformation() {
    String result = "";
    if (tagDataList.size() != 0) {
      for (Entity articleData : tagDataList) {
        result += articleData.getEntityInformation();
      }
      return result;
    } else {
      return info;
    }
  }

  /**
   * Get the Tag entity type
   * @return Tag
   */
  @Override
  public String getEntityType() {
    return "Tag";
  }
}
