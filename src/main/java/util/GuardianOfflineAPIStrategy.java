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
        String responseData = "";

        return responseDataJson;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(new GuardianOfflineAPIStrategy().login("1b0f84fb-9674-4fe2-b596-5836b2772fcb").toString());

//        System.out.println(new GuardianOnlineAPIStrategy().searchByTag("1b0f84fb-9674-4fe2-b596-5836b2772fcb", "sausage", 1).toString());
    }

}
