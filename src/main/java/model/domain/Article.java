package model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an Entity class that implements the Entity interface and stores Article data
 * @author Rachel Yang
 */
public class Article implements Entity {

  private String id;
  private String webTitle;
  private List<Entity> articleDataList = new ArrayList<>();
  private String relatedTag;

  private String info;

  /**
   * Getter for getting Article ID
   * @return Article ID
   */
  public String getId() {
    return id;
  }

  /**
   * Setter method for setting Article ID
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Getter method for getting Article webTitle
   * @return Article webTitle
   */
  public String getWebTitle() {
    return webTitle;
  }

  /**
   * Setter method for setting Article webTitle
   */
  public void setWebTitle(String webTitle) {
    this.webTitle = webTitle;
  }

  /**
   * Getter method for getting Article articleDataList
   * @return Article articleDataList
   */
  public List<Entity> getArticleDataList() {
    return articleDataList;
  }

  /**
   * Setter method for setting Article articleDataList
   */
  public void setArticleDataList(List<Entity> articleDataList) {
    this.articleDataList = articleDataList;
  }

  /**
   * Add articleData to articleDataList
   */
  public void addArticleDataList(ArticleData articleData) {
    this.articleDataList.add(articleData);
  }

  /**
   * Getter method  for getting  Article articleDataList
   * @return Article articleDataList
   */
  public String getRelatedTag() {
    return relatedTag;
  }

  /**
   * Setter method for setting Article relatedTag
   */
  public void setRelatedTag(String relatedTag) {
    this.relatedTag = relatedTag;
  }

  /**
   * Getter method for getting  Article info
   * @return Article info
   */
  public String getInfo() {
    return info;
  }

  /**
   * Setter method for setting Article info
   */
  public void setInfo(String info) {
    this.info = info;
  }

  /**
   * Get the information about Article
   * @return Information about Article
   */
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

  /**
   * Get the Article entity type
   * @return Article
   */
  @Override
  public String getEntityType() {
    return "Article";
  }
}
