package model.domain;

public class TagData implements Entity {
  private String dataAttribute;
  private String dataContent;

  /**
   * Getter method for getting TagData dataAttribute
   * @return TagData dataAttribute
   */
  public String getDataAttribute() {
    return dataAttribute;
  }

  /**
   * Setter method for setting TagData dataAttribute
   */
  public void setDataAttribute(String dataAttribute) {
    this.dataAttribute = dataAttribute;
  }

  /**
   * Getter method for getting TagData dataContent
   * @return TagData dataContent
   */
  public String getDataContent() {
    return dataContent;
  }

  /**
   * Setter method for setting TagData dataContent
   */
  public void setDataContent(String dataContent) {
    this.dataContent = dataContent;
  }

  /**
   * Get the information about TagData
   * @return Information about TagData
   */
  @Override
  public String getEntityInformation() {
    String result = dataAttribute + ": " + dataContent + "\n";
    return result;
  }

  /**
   * Get the TagData entity type
   * @return TagData
   */
  @Override
  public String getEntityType() {
    return "TagData";
  }
}
