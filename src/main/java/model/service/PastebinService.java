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

public class PastebinService {

    private EntityCollectionFactory entityCollectionFactory;
    private EntityFactory defaultErrorFactory;
    private EntityFactory entityFactory;
    private User user;
    private PastebinAPIStrategy pastebinAPIStrategy;

    public PastebinService(PastebinAPIStrategy pastebinAPIStrategy) {
        this.pastebinAPIStrategy = pastebinAPIStrategy;
        this.defaultErrorFactory = new DefaultErrorInfoFactory();
        this.entityCollectionFactory = new ArticleFactory();
    }

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

        return returnEntity;    }

    public static void main(String[] args) {
        Entity e = new PastebinService(new PastebinOfflineAPIStrategy()).getPastebinLink("agr_jX9Bg7kE3-XgRLIt-TWk2teKqTxN", "PastebinOnlineAPIStrategy successful!");
        System.out.println(e.getEntityInformation());
    }
}
