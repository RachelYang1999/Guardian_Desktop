package model.domain;

import java.util.List;

public class Article implements Entity{

    private String webTitle;
    private List<Entity> articleDataList;

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public List<Entity> getArticleDataList() {
        return articleDataList;
    }

    public void setArticleDataList(List<Entity> articleDataList) {
        this.articleDataList = articleDataList;
    }

    public void addArticleDataList(ArticleData articleData) {
        this.articleDataList.add(articleData);
    }

    @Override
    public String getEntityInformation() {
        String result = "";
        for (Entity articleData : articleDataList) {
            result += articleData.getEntityInformation();
        }
        return result;
    }

    @Override
    public String getEntityType() {
        return "Article";
    }
}
