package model.domain;

/**
 * This is an Entity class that implements the Entity interface and stores Article metadata
 * @author Rachel Yang
 */
public class ArticleData implements Entity {
  private String dataAttribute;
  private String dataContent;

  /**
   * Getter method of Article dataAttribute
   * @return Article dataAttribute
   */
  public String getDataAttribute() {
    return dataAttribute;
  }


  /**
   * Setter method of Article metadata attribute
   */
  public void setDataAttribute(String dataAttribute) {
    this.dataAttribute = dataAttribute;
  }

  /**
   * Getter method of Article metadata content
   * @return Article metadata content
   */
  public String getDataContent() {
    return dataContent;
  }

  /**
   * Setter method of Article metadata content
   */
  public void setDataContent(String dataContent) {
    this.dataContent = dataContent;
  }

  /**
   * Get the information about ArticleData
   * @return Information about ArticleData
   */
  @Override
  public String getEntityInformation() {
    String result = dataAttribute + ": " + dataContent + "\n";
    return result;
  }

  /**
   * Get the ArticleData entity type
   * @return ArticleData
   */
  @Override
  public String getEntityType() {
    return "ArticleData";
  }
}
