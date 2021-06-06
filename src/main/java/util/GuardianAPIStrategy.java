package util;

import org.json.JSONObject;

/**
 * The util interface for providing online or offline GuardianAPI fetching strategy
 * @author Rachel Yang
 */
public interface GuardianAPIStrategy {

  /**
   *
   * @param token The token for authorization to login from the API
   * @return The JSONObject with response info
   */
  JSONObject login(String token);

  /**
   * This method is for search all Tags by the input keyword
   * @param token The token for authorization to search tags from the API
   * @param tag The keyword which will be matched for searching tags
   * @param pageNumber The page number which will be fetched for api response
   * @return The list of entity which store the response information
   */
  JSONObject searchTagsByKeyword(String token, String tag, int pageNumber);

  /**
   * Thie method is for search all Tags by the input keyword
   * @param token The token for authorization to search articles from the API
   * @param tag The tag which will be matched for searching tags
   * @param pageNumber The page number which will be fetched for api response
   * @return The list of entity which store the response information
   */
  JSONObject searchArticlesByTag(String token, String tag, int pageNumber);
}
