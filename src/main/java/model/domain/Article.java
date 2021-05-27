package model.domain;

public class Article implements Entity{

    private String id;
    private String type;
    private String sectionID;
    private String sectionName;
    private String webTitle;
    private String webUrl;
    private String apiUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", sectionID='" + sectionID + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", webTitle='" + webTitle + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                '}';
    }

    @Override
    public String getEntityInformation() {
        String result =
                "Article ID: " + id + "\n" +
                "Article Type: " + type + "\n" +
                "Section ID: " + sectionID + "\n" +
                "Section Name: " + sectionName + "\n" +
                "Web Title: " + webTitle + "\n" +
                "Web Url: " + webUrl + "\n" +
                "API Url: " + apiUrl + "\n";
        return result;
    }

    @Override
    public String getEntityType() {
        return "Article";
    }
}
