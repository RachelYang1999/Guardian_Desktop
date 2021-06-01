package util;

import okhttp3.*;
import org.json.JSONObject;

public class PastebinOnlineAPIStrategy implements PastebinAPIStrategy {
    private OkHttpClient client;
    private Response response;

    @Override
    public String getPastebinLink(String token, String copiedText) {
        String responseData = "";

        return responseData;
    }
}
