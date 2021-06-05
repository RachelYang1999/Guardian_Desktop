package model.domain;

/**
 * This is an Entity class that implements the Entity interface and stores Error message data
 * @author Rachel Yang
 */
public class ErrorInfo implements Entity {

  private String message;
  private int errorCode;
  private String relatedModule;

  /**
   * Getter method for getting error message
   * @return ErrorInfo message
   */
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Getter method for getting error code
   * @return ErrorInfo error code
   */
  public int getErrorCode() {
    return errorCode;
  }

  /**
   * Setter method for setting error code
   */
  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Getter method for getting error related module
   * @return ErrorInfo error related module
   */
  public String getRelatedModule() {
    return relatedModule;
  }

  /**
   * Setter method for setting error related module
   */
  public void setRelatedModule(String relatedModule) {
    this.relatedModule = relatedModule;
  }

  /**
   * Get the information about ErrorInfo
   * @return Information about ErrorInfo
   */
  @Override
  public String getEntityInformation() {
    return "Error Message: " + this.message + "\n";
  }

  /**
   * Get the ErrorInfo entity type
   * @return ErrorInfo
   */
  @Override
  public String getEntityType() {
    return "ErrorInfo";
  }
}
