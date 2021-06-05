package model.domain;

public class Response implements Entity {

  String info;

  /**
   * Getter method for getting Response info
   * @return Response info
   */
  public String getInfo() {
    return info;
  }

  /**
   * Setter method for setting Response info
   */
  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return info;
  }

  /**
   * Get the information about Response
   * @return Information about Response
   */
  @Override
  public String getEntityInformation() {
    return "Response Information: " + this.info;
  }

  /**
   * Get the Response entity type
   * @return Response
   */
  @Override
  public String getEntityType() {
    return "Response";
  }
}
