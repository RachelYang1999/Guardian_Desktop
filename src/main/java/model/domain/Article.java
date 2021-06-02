package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Article implements Entity {

  private String id;
  private String webTitle;
  private List<Entity> articleDataList = new ArrayList<>();
  private String relatedTag;

  private String info;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWebTitle() {
    return webTitle;
  }

  public void setWebTitle(String webTitle) {
    this.webTitle = webTitle;
  }

  public List<Entity> getArticleDataList() {
    return articleDataList;
  }

  public void setArticleDataList(List<Entity> articleDataList) {
    this.articleDataList = articleDataList;
  }

  public void addArticleDataList(ArticleData articleData) {
    this.articleDataList.add(articleData);
  }

  public String getRelatedTag() {
    return relatedTag;
  }

  public void setRelatedTag(String relatedTag) {
    this.relatedTag = relatedTag;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public String getEntityInformation() {
    String result = "";
    if (articleDataList.size() != 0) {
      for (Entity articleData : articleDataList) {
        result += articleData.getEntityInformation();
      }
      return result;
    } else {
      return info;
    }
  }

  @Override
  public String getEntityType() {
    return "Article";
  }
}
