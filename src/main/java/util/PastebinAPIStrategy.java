package util;

public interface PastebinAPIStrategy {

  String getPastebinLink(String token, String copiedText);
}
