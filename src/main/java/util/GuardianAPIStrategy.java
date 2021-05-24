package util;

import org.json.JSONObject;

public interface GuardianAPIStrategy {
    JSONObject login(String token);

    JSONObject searchByTag(String token, String tag, int pageNumber);
}
