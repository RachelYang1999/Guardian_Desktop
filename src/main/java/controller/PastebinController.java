package controller;

import model.domain.Entity;
import model.domain.Pastebin;
import model.service.ArticleService;
import model.service.PastebinService;
import util.GuardianAPIStrategy;
import util.PastebinAPIStrategy;

import java.util.List;

public class PastebinController {

  private PastebinService pastebinService;

  public PastebinController(PastebinAPIStrategy pastebinAPIStrategy) {
    this.pastebinService = new PastebinService(pastebinAPIStrategy);
  }

  public Entity getPastebinLink(String token, String copiedText) {

    return pastebinService.getPastebinLink(token, copiedText);
  }
}
