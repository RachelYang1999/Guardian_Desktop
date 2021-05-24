package strategy;

import okhttp3.*;
import org.json.JSONObject;

public class GuardianOnlineAPIStrategy {
    private OkHttpClient client;
    private Response response;
    private String token;

    public JSONObject login(String token) {
        JSONObject responseDataJson = null;
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://content.guardianapis.com/tags?api-key=" + token)
                    .method("GET", null)
                    .build();
            response = client.newCall(request).execute();
            responseData = response.body().string();
            responseDataJson = new JSONObject(responseData);
            System.out.println("[GuardianOnlineUtil] login responseData " + responseData);
            System.out.println("[GuardianOnlineUtil] login response.code() " + response.code());
            System.out.println("[GuardianOnlineUtil] login ----------------------------------------");

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseDataJson;
    }

    public JSONObject searchByTag(String token, String tag) {
        JSONObject responseDataJson = null;
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://content.guardianapis.com/tags?api-key=" + token)
                    .method("GET", null)
                    .build();
            response = client.newCall(request).execute();
            responseData = response.body().string();
            responseDataJson = new JSONObject(responseData);
            System.out.println("[GuardianOnlineUtil] login responseData " + responseData);
            System.out.println("[GuardianOnlineUtil] login response.code() " + response.code());
            System.out.println("[GuardianOnlineUtil] login ----------------------------------------");

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseDataJson;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(new GuardianOnlineAPIStrategy().login("1b0f84fb-9674-4fe2-b596-5836b2772fcb").toString());
    }


}
