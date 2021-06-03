package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Tag implements Entity {

  private String tagName;
  private List<Entity> tagDataList = new ArrayList<>();
  private String info;

  private String relatedKeyword;


  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public List<Entity> getTagDataList() {
    return tagDataList;
  }

  public void setTagDataList(List<Entity> tagDataList) {
    this.tagDataList = tagDataList;
  }

  public void addTagDataList(TagData tagData) {
    this.tagDataList.add(tagData);
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getRelatedKeyword() {
    return relatedKeyword;
  }

  public void setRelatedKeyword(String relatedKeyword) {
    this.relatedKeyword = relatedKeyword;
  }

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

  @Override
  public String getEntityType() {
    return "Tag";
  }
}
