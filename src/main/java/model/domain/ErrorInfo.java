package model.domain;

public class ErrorInfo implements Entity {

  private String message;
  private int errorCode;
  private String relatedModule;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getRelatedModule() {
    return relatedModule;
  }

  public void setRelatedModule(String relatedModule) {
    this.relatedModule = relatedModule;
  }

  @Override
  public String getEntityInformation() {
    return "Error Message: " + this.message + "\n";
  }

  @Override
  public String getEntityType() {
    return "ErrorInfo";
  }
}
