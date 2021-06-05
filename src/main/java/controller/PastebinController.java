package controller;

import model.domain.Entity;
import model.domain.Pastebin;
import model.service.ArticleService;
import model.service.PastebinService;
import util.GuardianAPIStrategy;
import util.PastebinAPIStrategy;

import java.util.List;

/**
 * The Controller of the Entity Pastebin with the use of MVC pattern
 * @author Rachel Yang
 */
public class PastebinController {

  private PastebinService pastebinService;

  /**
   * The constructor of ArticleController
   * @param pastebinAPIStrategy The API fetching strategy to be injected into the construction of PastebinController
   */
  public PastebinController(PastebinAPIStrategy pastebinAPIStrategy) {
    this.pastebinService = new PastebinService(pastebinAPIStrategy);
  }

  /**
   * Method for getting the pastebin link
   * @param token The token for authorization to get the Pastebin link from the API
   * @param copiedText The text content to be copy and pasted in the Pastebin link
   * @return The Entity Pastebin stores the valid pastebin link and other info
   */
  public Entity getPastebinLink(String token, String copiedText) {

    return pastebinService.getPastebinLink(token, copiedText);
  }
}
