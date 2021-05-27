package util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class GuardianOfflineAPIStrategy implements GuardianAPIStrategy {
    private OkHttpClient client;
    private Response response;
    private String token;

    public JSONObject login(String token) {
        JSONObject responseDataJson = null;
        String responseData = "{\"response\":{\"status\": \"ok\",\"userTier\":\"developer\",\"total\":63957,\"startIndex\":1,\"pages\":6396,\"pageSize\":10,\"currentPage\":1}}";
        responseDataJson = new JSONObject(responseData);
        return responseDataJson;
    }

    public JSONObject searchByTag(String token, String tag, int pageNumber) {
        JSONObject responseDataJson = null;
//        String responseData = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1," +
//                "\"results\":[" +
//                "{\"id\":\"food/sausages\",\"type\":\"keyword\",\"sectionId\":\"food\",\"sectionName\":\"Food\",\"webTitle\":\"Sausages\",\"webUrl\":\"https://www.theguardian.com/food/sausages\",\"apiUrl\":\"https://content.guardianapis.com/food/sausages\"}," +
//                "{\"id\":\"film/sausage-party\",\"type\":\"keyword\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webTitle\":\"Sausage Party\",\"webUrl\":\"https://www.theguardian.com/film/sausage-party\",\"apiUrl\":\"https://content.guardianapis.com/film/sausage-party\"}" +
//                "]}}";
        String responseData = "{\"idk\":\"irregular response\"}";
        responseDataJson = new JSONObject(responseData);
        return responseDataJson;
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(new GuardianOfflineAPIStrategy().login("1b0f84fb-9674-4fe2-b596-5836b2772fcb").toString());

        System.out.println(new GuardianOfflineAPIStrategy().searchByTag("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "sausage", 1).toString());
    }

}
