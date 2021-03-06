package util;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class GuardianOnlineAPIStrategy implements GuardianAPIStrategy {
  private OkHttpClient client;
  private Response response;
  private String token;

  /**
   *
   * @param token The token for authorization to login from the API
   * @return The JSONObject with response info
   */
  @Override
  public JSONObject login(String token) {
    JSONObject responseDataJson = null;
    String responseData = "";
    try {
      client = new OkHttpClient().newBuilder().build();
      Request request =
          new Request.Builder()
              .url("http://content.guardianapis.com/tags?api-key=" + token)
              .method("GET", null)
              .build();
      response = client.newCall(request).execute();
      responseData = response.body().string();
      responseDataJson = new JSONObject(responseData);

      System.out.println("[GuardianOnlineUtil] login" + " responseData " + responseData);
      System.out.println("[GuardianOnlineUtil] login" + " response.code() " + response.code());
      System.out.println(
          "[GuardianOnlineUtil] login"
              + " ----------------------------------------------------------------");

      response.body().close();
      client.connectionPool().evictAll();
    } catch (Exception e) {
      System.err.println("Something got wrong");
      e.printStackTrace();
    } finally {
      if (response != null && client != null) {
        response.body().close();
        client.connectionPool().evictAll();
      }
    }
    return responseDataJson;
  }

  /**
   * This method is for search all Tags by the input keyword
   * @param token The token for authorization to search tags from the API
   * @param tag The keyword which will be matched for searching tags
   * @param pageNumber The page number which will be fetched for api response
   * @return The list of entity which store the response information
   */
  @Override
  public JSONObject searchTagsByKeyword(String token, String tag, int pageNumber) {
    JSONObject responseDataJson = null;
    String responseData = "";
    try {
      client = new OkHttpClient().newBuilder().build();
      Request request =
          new Request.Builder()
              .url(
                  "http://content.guardianapis.com/tags?api-key="
                      + token
                      + "&q="
                      + tag
                      + "&page="
                      + Integer.toString(pageNumber))
              .method("GET", null)
              .build();

      response = client.newCall(request).execute();
      responseData = response.body().string();
      responseDataJson = new JSONObject(responseData);

      System.out.println("[GuardianOnlineUtil] searchByTag" + " responseData " + responseData);
      System.out.println(
          "[GuardianOnlineUtil] searchByTag" + " response.code() " + response.code());
      System.out.println(
          "[GuardianOnlineUtil] searchByTag"
              + " ----------------------------------------------------------------");

      response.body().close();
      client.connectionPool().evictAll();

    } catch (Exception e) {
      System.err.println("Something got wrong");
      e.printStackTrace();
    }
    if (response.body() != null && client != null) {
      response.body().close();
      client.connectionPool().evictAll();
    }
    return responseDataJson;
  }

  /**
   * Thie method is for search all Tags by the input keyword
   * @param token The token for authorization to search articles from the API
   * @param tag The tag which will be matched for searching tags
   * @param pageNumber The page number which will be fetched for api response
   * @return The list of entity which store the response information
   */
  @Override
  public JSONObject searchArticlesByTag(String token, String tag, int pageNumber) {
    JSONObject responseDataJson = null;
    String responseData = "";
    try {
      client = new OkHttpClient().newBuilder().build();
      Request request = new Request.Builder()
              .url("https://content.guardianapis.com/" + tag + "?page=" + pageNumber + "&api-key=" + token)
              .method("GET", null)
              .build();

      response = client.newCall(request).execute();
      responseData = response.body().string();
      responseDataJson = new JSONObject(responseData);

      System.out.println("[GuardianOnlineUtil] getArticles" + " responseData " + responseData);
      System.out.println(
              "[GuardianOnlineUtil] getArticles" + " response.code() " + response.code());
      System.out.println(
              "[GuardianOnlineUtil] getArticles"
                      + " ----------------------------------------------------------------");

      response.body().close();
      client.connectionPool().evictAll();

    } catch (Exception e) {
      System.err.println("Something got wrong");
      e.printStackTrace();
    }
    if (response.body() != null && client != null) {
      response.body().close();
      client.connectionPool().evictAll();
    }
    return responseDataJson;
  }

//  public static void main(String[] args) throws Exception {
//    //        System.out.println(new
//    // GuardianOnlineAPIStrategy().login("1b0f84fb-9674-4fe2-b596-5836b2772fcb").toString());
//
//    System.out.println(
//        new GuardianOnlineAPIStrategy()
//            .searchTagsByKeyword("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "gay", 1)
//            .toString());
//
////    System.out.println(
////            new GuardianOnlineAPIStrategy()
////                    .getArticles("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "food/food", 1)
////                    .toString());
//  }
}
