package util;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONObject;

public class GuardianOfflineAPIStrategy implements GuardianAPIStrategy {
  private OkHttpClient client;
  private Response response;
  private String token;

  public JSONObject login(String token) {
    JSONObject responseDataJson = null;
    String responseData =
        "{\"response\":{\"status\": \"ok\",\"userTier\":\"developer\",\"total\":63957,\"startIndex\":1,\"pages\":6396,\"pageSize\":10,\"currentPage\":1}}";
    responseDataJson = new JSONObject(responseData);
    return responseDataJson;
  }

  public JSONObject searchTagsByKeyword(String token, String tag, int pageNumber) {
    JSONObject responseDataJson = null;
    String responseData =
     "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1," +
                    "\"results\":[" +

     "{\"id\":\"food/sausages\",\"type\":\"keyword\",\"sectionId\":\"food\",\"sectionName\":\"Food\",\"webTitle\":\"Sausages\",\"webUrl\":\"https://www.theguardian.com/food/sausages\",\"apiUrl\":\"https://content.guardianapis.com/food/sausages\"}," +

     "{\"id\":\"film/sausage-party\",\"type\":\"keyword\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webTitle\":\"Sausage Party\",\"webUrl\":\"https://www.theguardian.com/film/sausage-party\",\"apiUrl\":\"https://content.guardianapis.com/film/sausage-party\"}" +
                    "]}}";
//    String responseData = "{\"idk\":\"irregular response\"}";
    responseDataJson = new JSONObject(responseData);
    return responseDataJson;
  }

  @Override
  public JSONObject searchArticlesByTag(String token, String articlesAPI, int pageNumber) {
    JSONObject responseDataJson = null;
    String responseData = "{\"response\":" +
            "{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":1,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1,\"orderBy\":\"newest\"," +
            "\"tag\":{\"id\":\"books/roxane-gay\",\"type\":\"keyword\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webTitle\":\"Roxane Gay\",\"webUrl\":\"https://www.theguardian.com/books/roxane-gay\",\"apiUrl\":\"https://content.guardianapis.com/books/roxane-gay\"}," +
            "\"results\":[{\"id\":\"artanddesign/2021/mar/03/pubic-hair-paintings-living-room-womens-sexuality-right-to-pleasure-camera-vagina\",\"type\":\"article\",\"sectionId\":\"artanddesign\",\"sectionName\":\"Art and design\",\"webPublicationDate\":\"2021-03-03T06:00:11Z\",\"webTitle\":\"'My pubic hair paintings could hang in your living room': the artists reclaiming women's sexuality\",\"webUrl\":\"https://www.theguardian.com/artanddesign/2021/mar/03/pubic-hair-paintings-living-room-womens-sexuality-right-to-pleasure-camera-vagina\",\"apiUrl\":\"https://content.guardianapis.com/artanddesign/2021/mar/03/pubic-hair-paintings-living-room-womens-sexuality-right-to-pleasure-camera-vagina\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}]}}";
    responseDataJson = new JSONObject(responseData);
    return responseDataJson;
  }

  public static void main(String[] args) throws Exception {
    //        System.out.println(new
    // GuardianOfflineAPIStrategy().login("1b0f84fb-9674-4fe2-b596-5836b2772fcb").toString());

    System.out.println(
        new GuardianOfflineAPIStrategy()
            .searchTagsByKeyword("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "sausage", 1)
            .toString());
  }
}
