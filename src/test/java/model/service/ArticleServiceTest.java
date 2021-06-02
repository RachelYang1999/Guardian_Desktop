package model.service;

import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.domain.Entity;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.GuardianAPIStrategy;
import util.GuardianOfflineAPIStrategy;
import util.GuardianOnlineAPIStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ArticleServiceTest {
    private GuardianAPIStrategy onlineGuardianAPIStrategy;
    private GuardianAPIStrategy offlineGuardianAPIStrategy;

    private ArticleDao articleDao;

    @Before
    public void init() {
        String dummyResponseSuccessful =
                "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1," +
                        "\"results\":[" +
                        "{\"id\":\"food/sausages\",\"type\":\"keyword\",\"sectionId\":\"food\",\"sectionName\":\"Food\",\"webTitle\":\"Sausages\",\"webUrl\":\"https://www.theguardian.com/food/sausages\",\"apiUrl\":\"https://content.guardianapis.com/food/sausages\"}," +
                        "{\"id\":\"film/sausage-party\",\"type\":\"keyword\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webTitle\":\"Sausage Party\",\"webUrl\":\"https://www.theguardian.com/film/sausage-party\",\"apiUrl\":\"https://content.guardianapis.com/film/sausage-party\"}" +
                        "]}}";
        String dummyResponseInvalidToken = "{\"message\":\"Invalid authentication credentials\"}";
        String dummyResponseNoResult = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":0,\"startIndex\":0,\"pageSize\":10,\"currentPage\":1,\"pages\":0,\"results\":[]}}";
        String dummyResponseIrregular = "{\"idk\":\"irregular response\"}";

        JSONObject successfulResponse = new JSONObject(dummyResponseSuccessful);
        JSONObject invalidTokenResponse = new JSONObject(dummyResponseInvalidToken);
        JSONObject noResultResponse = new JSONObject(dummyResponseNoResult);
        JSONObject irregularResponse = new JSONObject(dummyResponseIrregular);


        onlineGuardianAPIStrategy = Mockito.mock(GuardianOnlineAPIStrategy.class);
        offlineGuardianAPIStrategy = Mockito.mock(GuardianOfflineAPIStrategy.class);

        articleDao = Mockito.mock(ArticleDao.class);

        when(onlineGuardianAPIStrategy.searchByTag("correct token", "sausage", 1)).thenReturn(successfulResponse);
        when(offlineGuardianAPIStrategy.searchByTag("correct token", "sausage", 1)).thenReturn(successfulResponse);

        when(onlineGuardianAPIStrategy.searchByTag("incorrect token", "sausage", 1)).thenReturn(invalidTokenResponse);
        when(offlineGuardianAPIStrategy.searchByTag("incorrect token", "sausage", 1)).thenReturn(invalidTokenResponse);

        when(onlineGuardianAPIStrategy.searchByTag("correct token", "no result tag", 1)).thenReturn(noResultResponse);
        when(offlineGuardianAPIStrategy.searchByTag("correct token", "no result tag", 1)).thenReturn(noResultResponse);

        when(onlineGuardianAPIStrategy.searchByTag("correct token", "irregular tag", 1)).thenReturn(irregularResponse);
        when(offlineGuardianAPIStrategy.searchByTag("correct token", "irregular tag", 1)).thenReturn(irregularResponse);

        List<String> resultList =  new ArrayList<>();
        resultList.add(("exist info"));
        when(articleDao.getEntity(anyString(), anyString(), anyString())).thenReturn(resultList);
//        when(articleDao.getEntity(anyString(), eq("non-exist tag"))).thenReturn("");

        when(articleDao.addEntity(any())).thenReturn(true);

    }


    @Test
    public void testSearchSuccessOnline() {
        ArticleService articleService = new ArticleService(onlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = articleService.searchByTag("correct token", "sausage", 1);
        String result = "";
        for (Entity e: returnedEntities) {
            result += e.getEntityInformation();
            assertEquals("Article", e.getEntityType());
        }
        String expected = "sectionName: Food\n" +
                "apiUrl: https://content.guardianapis.com/food/sausages\n" +
                "webUrl: https://www.theguardian.com/food/sausages\n" +
                "webTitle: Sausages\n" +
                "id: food/sausages\n" +
                "sectionId: food\n" +
                "type: keyword\n" +
                "sectionName: Film\n" +
                "apiUrl: https://content.guardianapis.com/film/sausage-party\n" +
                "webUrl: https://www.theguardian.com/film/sausage-party\n" +
                "webTitle: Sausage Party\n" +
                "id: film/sausage-party\n" +
                "sectionId: film\n" +
                "type: keyword\n";
        assertEquals(expected, result);
    }

    @Test
    public void testSearchSuccessOffline() {
        ArticleService userService = new ArticleService(offlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("correct token", "sausage", 1);
        String result = "";
        for (Entity e: returnedEntities) {
            result += e.getEntityInformation();
            assertEquals("Article", e.getEntityType());
        }
        String expected = "sectionName: Food\n" +
                "apiUrl: https://content.guardianapis.com/food/sausages\n" +
                "webUrl: https://www.theguardian.com/food/sausages\n" +
                "webTitle: Sausages\n" +
                "id: food/sausages\n" +
                "sectionId: food\n" +
                "type: keyword\n" +
                "sectionName: Film\n" +
                "apiUrl: https://content.guardianapis.com/film/sausage-party\n" +
                "webUrl: https://www.theguardian.com/film/sausage-party\n" +
                "webTitle: Sausage Party\n" +
                "id: film/sausage-party\n" +
                "sectionId: film\n" +
                "type: keyword\n";
        assertEquals(expected, result);
    }

    @Test
    public void testSearchInvalidTokenOnline() {
        ArticleService userService = new ArticleService(onlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("incorrect token", "sausage", 1);

        assertEquals(1, returnedEntities.size());

        String result = "";
        for (Entity e: returnedEntities) {
            result += e.getEntityInformation();
            assertEquals("ErrorInfo", e.getEntityType());
        }

        String expected = "Error Message: Invalid authentication credentials" + "\n";
        assertEquals(expected, result);
    }

    @Test
    public void testSearchInvalidTokenOffline() {
        ArticleService userService = new ArticleService(offlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("incorrect token", "sausage", 1);

        assertEquals(1, returnedEntities.size());

        String result = "";
        for (Entity e: returnedEntities) {
            result += e.getEntityInformation();
            assertEquals("ErrorInfo", e.getEntityType());
        }

        String expected = "Error Message: Invalid authentication credentials" + "\n";
        assertEquals(expected, result);
    }

    @Test
    public void testSearchNoResultOnline() {
        ArticleService userService = new ArticleService(onlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("correct token", "no result tag", 1);
        assertEquals(0, returnedEntities.size());
    }

    @Test
    public void testSearchNoResultOffline() {
        ArticleService userService = new ArticleService(offlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("correct token", "no result tag", 1);
        assertEquals(0, returnedEntities.size());
    }

    @Test
    public void testSearchIrregularOnline() {
        ArticleService userService = new ArticleService(onlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("correct token", "irregular tag", 1);
        assertEquals(1, returnedEntities.size());

        String result = "";
        for (Entity e: returnedEntities) {
            result += e.getEntityInformation();
            assertEquals("ErrorInfo", e.getEntityType());
        }
        String expected = "Error Message: Unknown Error!" + "\n";
        assertEquals(expected, result);
    }

    @Test
    public void testSearchIrregularOffline() {
        ArticleService userService = new ArticleService(offlineGuardianAPIStrategy, articleDao);
        List<Entity> returnedEntities = userService.searchByTag("correct token", "irregular tag", 1);
        assertEquals(1, returnedEntities.size());

        String result = "";
        for (Entity e: returnedEntities) {
            result += e.getEntityInformation();
            assertEquals("ErrorInfo", e.getEntityType());
        }
        String expected = "Error Message: Unknown Error!" + "\n";
        assertEquals(expected, result);
    }
}
