package util;

import okhttp3.*;

public class PastebinOfflineAPIStrategy implements PastebinAPIStrategy {
  private OkHttpClient client;
  private Response response;

  @Override
  public String getPastebinLink(String token, String copiedText) {
    String responseData = "https://pastebin.com/fkE8qhUd";

    return responseData;
  }

  public static void main(String[] args) throws Exception {
    System.out.println(
        new PastebinOfflineAPIStrategy()
            .getPastebinLink(
                "agr_jX9Bg7kE3-XgRLIt-TWk2teKqTxN", "PastebinOnlineAPIStrategy successful!"));
  }
}
