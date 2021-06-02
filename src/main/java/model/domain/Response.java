package model.domain;

public class Response implements Entity {

  String info;

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return info;
  }

  @Override
  public String getEntityInformation() {
    return "Response Information: " + this.info;
  }

  @Override
  public String getEntityType() {
    return "Response";
  }
}
