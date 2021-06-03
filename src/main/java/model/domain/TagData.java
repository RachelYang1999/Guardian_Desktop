package model.domain;

public class TagData implements Entity {
  private String dataAttribute;
  private String dataContent;

  public String getDataAttribute() {
    return dataAttribute;
  }

  public void setDataAttribute(String dataAttribute) {
    this.dataAttribute = dataAttribute;
  }

  public String getDataContent() {
    return dataContent;
  }

  public void setDataContent(String dataContent) {
    this.dataContent = dataContent;
  }

  @Override
  public String getEntityInformation() {
    String result = dataAttribute + ": " + dataContent + "\n";
    return result;
  }

  @Override
  public String getEntityType() {
    return "ArticleData";
  }
}
