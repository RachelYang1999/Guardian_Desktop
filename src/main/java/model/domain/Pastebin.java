package model.domain;

public class Pastebin implements Entity {
  private String link;
  private String token;

  /**
   * Getter method for getting Pastebin link
   * @return Pastebin link
   */
  public String getLink() {
    return link;
  }

  /**
   * Setter method for setting Pastebin link
   */
  public void setLink(String link) {
    this.link = link;
  }

  /**
   * Getter method for getting Pastebin token
   * @return Pastebin token
   */
  public String getToken() {
    return token;
  }

  /**
   * Setter method for setting Pastebin token
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * Get the information about Pastebin
   * @return Information about Pastebin
   */
  @Override
  public String getEntityInformation() {
    String result =
        //                "Token: " + token + "\n" +
        "Link: " + link + "\n";
    return result;
  }

  /**
   * Get the Pastebin entity type
   * @return Pastebin
   */
  @Override
  public String getEntityType() {
    return "Pastebin";
  }
}
