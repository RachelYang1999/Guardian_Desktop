package util;

import org.json.JSONObject;

public interface GuardianAPIStrategy {
  JSONObject login(String token);

  JSONObject searchTagsByKeyword(String token, String tag, int pageNumber);

  JSONObject searchArticlesByTag(String token, String tag, int pageNumber);
}
