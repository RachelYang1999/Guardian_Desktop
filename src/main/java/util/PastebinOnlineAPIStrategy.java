package util;

import okhttp3.*;
import org.json.JSONObject;

public class PastebinOnlineAPIStrategy implements PastebinAPIStrategy {
    private OkHttpClient client;
    private Response response;

    @Override
    public String getPastebinLink(String token, String copiedText) {
        String responseData = "";
        try {
            client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("api_dev_key", token)
                    .addFormDataPart("api_option", "paste")
                    .addFormDataPart("api_paste_code", copiedText)
                    .build();
            Request request = new Request.Builder()
                    .url("https://pastebin.com/api/api_post.php")
                    .method("POST", body)
                    .build();
            response = client.newCall(request).execute();
            responseData = response.body().string();

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response.body() != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }
}
