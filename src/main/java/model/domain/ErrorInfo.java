package model.domain;

public class ErrorInfo implements Entity {

    private String message;
    private int errorCode;
    private String relatedModule;

    public String getMessage() {
        return null;
    }

    public void setMessage(String message) {
    }

    public int getErrorCode() {
        return -1;
    }

    public void setErrorCode(int errorCode) {
    }

    public String getRelatedModule() {
        return null;
    }

    public void setRelatedModule(String relatedModule) {
    }

    @Override
    public String getEntityInformation() {
        return null;
    }

    @Override
    public String getEntityType() {
        return null;
    }
}
