package util;

import okhttp3.*;

public class PastebinOfflineAPIStrategy implements PastebinAPIStrategy {
  private OkHttpClient client;
  private Response response;

  @Override
  public String getPastebinLink(String token, String copiedText) {
    String responseData = "https://pastebin.com/GxQD8u5G";
    return responseData;
  }
}
