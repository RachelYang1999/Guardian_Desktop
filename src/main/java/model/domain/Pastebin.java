package model.domain;

public class Pastebin implements Entity {
    private String link;
    private String token;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getEntityInformation() {
        String result =
//                "Token: " + token + "\n" +
                "Link: " + link + "\n";
        return result;
    }

    @Override
    public String getEntityType() {
        return "Pastebin";
    }
}
