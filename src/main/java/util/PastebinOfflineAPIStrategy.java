package util;

import okhttp3.*;

/**
 * The util interface for providing offline PastebinAPI fetching strategy
 * @author Rachel Yang
 */
public class PastebinOfflineAPIStrategy implements PastebinAPIStrategy {
  private OkHttpClient client;
  private Response response;

  /**
   * This method is for search all Tags by the input keyword
   * @param token The token for authorization to login the API
   * @param copiedText The text to be copied and pasted
   * @return The list of entity which store the response information
   */
  @Override
  public String getPastebinLink(String token, String copiedText) {
    String responseData = "https://pastebin.com/GxQD8u5G";
    return responseData;
  }
}
