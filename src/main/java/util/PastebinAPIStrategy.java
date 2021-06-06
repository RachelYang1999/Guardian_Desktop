package util;

/**
 * The util interface for providing online or offline PastebinAPI fetching strategy
 * @author Rachel Yang
 */
public interface PastebinAPIStrategy {

  /**
   * This method is for search all Tags by the input keyword
   * @param token The token for authorization to login the API
   * @param copiedText The text to be copied and pasted
   * @return The list of entity which store the response information
   */
  String getPastebinLink(String token, String copiedText);
}
