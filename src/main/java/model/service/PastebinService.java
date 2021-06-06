package model.service;

import factory.entityfactory.*;
import model.domain.Entity;
import model.domain.ErrorInfo;
import model.domain.Pastebin;
import model.domain.User;
import org.json.JSONObject;
import util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Model Service of the Entity Pastebin which encapsulated the business logic for searching articles by input tag from the API
 * @author Rachel Yang
 */
public class PastebinService {

  private EntityCollectionFactory entityCollectionFactory;
  private EntityFactory defaultErrorFactory;
  private EntityFactory entityFactory;
  private User user;
  private PastebinAPIStrategy pastebinAPIStrategy;

  /**
   * The constructor of PastebinService
   * @param pastebinAPIStrategy The API fetching strategy to be injected into the construction of PastebinService
   */
  public PastebinService(PastebinAPIStrategy pastebinAPIStrategy) {
    this.pastebinAPIStrategy = pastebinAPIStrategy;
    this.defaultErrorFactory = new DefaultErrorInfoFactory();
    this.entityCollectionFactory = new ArticleFactory();
  }


  /**
   * Method for getting the pastebin link
   * @param token The token for authorization to get the Pastebin link from the API
   * @param copiedText The text content to be copy and pasted in the Pastebin link
   * @return The Entity Pastebin stores the valid pastebin link and other info
   */
  public Entity getPastebinLink(String token, String copiedText) {
    Entity returnEntity = defaultErrorFactory.createEntity(null);
    String returnedMessage = pastebinAPIStrategy.getPastebinLink(token, copiedText);
    if (returnedMessage.contains("pastebin.com")) {
      Pastebin pastebin = new Pastebin();
      pastebin.setLink(returnedMessage);
      pastebin.setToken(token);
      returnEntity = pastebin;
    } else {
      ErrorInfo errorInfo = new ErrorInfo();
      errorInfo.setMessage(returnedMessage);
      returnEntity = errorInfo;
    }
    return returnEntity;
  }

}
