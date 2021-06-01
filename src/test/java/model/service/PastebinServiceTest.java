package model.service;

import model.domain.Entity;
import model.domain.Pastebin;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PastebinServiceTest {
    private PastebinAPIStrategy onlinePastebinAPIStrategy;
    private PastebinAPIStrategy offlinePastebinAPIStrategy;

    @Before
    public void init() {
        String dummyResponseSuccessful = "https://pastebin.com/fkE8qhUd";
        String dummyResponseInvalidToken = "Bad API request, invalid_api_dev_key";
        String dummyResponseIrregular = "Irregular response";

        onlinePastebinAPIStrategy = Mockito.mock(PastebinOnlineAPIStrategy.class);
        offlinePastebinAPIStrategy = Mockito.mock(PastebinOfflineAPIStrategy.class);

        when(onlinePastebinAPIStrategy.getPastebinLink("correct token", "correct content")).thenReturn(dummyResponseSuccessful);
        when(offlinePastebinAPIStrategy.getPastebinLink("correct token", "correct content")).thenReturn(dummyResponseSuccessful);

        when(onlinePastebinAPIStrategy.getPastebinLink("incorrect token", "correct content")).thenReturn(dummyResponseInvalidToken);
        when(offlinePastebinAPIStrategy.getPastebinLink("incorrect token", "correct content")).thenReturn(dummyResponseInvalidToken);

        when(onlinePastebinAPIStrategy.getPastebinLink("irregular token", "irregular content")).thenReturn(dummyResponseIrregular);
        when(offlinePastebinAPIStrategy.getPastebinLink("irregular token", "irregular content")).thenReturn(dummyResponseIrregular);
    }

    @Test
    public void testSearchSuccessOnline() {
        PastebinService pastebinService = new PastebinService(onlinePastebinAPIStrategy);

        Entity entity = pastebinService.getPastebinLink("correct token", "correct content");
        String result = entity.getEntityInformation();
        String expected = "Link: https://pastebin.com/fkE8qhUd\n";
        assertEquals(expected, result);
        assertEquals("Pastebin", entity.getEntityType());

    }

    @Test
    public void testSearchSuccessOffline() {
        PastebinService pastebinService = new PastebinService(offlinePastebinAPIStrategy);

        Entity entity = pastebinService.getPastebinLink("correct token", "correct content");
        String result = entity.getEntityInformation();
        String expected = "Link: https://pastebin.com/fkE8qhUd\n";
        assertEquals(expected, result);
        assertEquals("Pastebin", entity.getEntityType());
    }

    @Test
    public void testSearchInvalidTokenOnline() {
        PastebinService pastebinService = new PastebinService(onlinePastebinAPIStrategy);

        Entity entity = pastebinService.getPastebinLink("incorrect token", "correct content");
        String result = entity.getEntityInformation();
        String expected = "Error Message: Bad API request, invalid_api_dev_key\n";
        assertEquals(expected, result);
        assertEquals("ErrorInfo", entity.getEntityType());
    }

    @Test
    public void testSearchInvalidTokenOffline() {
        PastebinService pastebinService = new PastebinService(offlinePastebinAPIStrategy);

        Entity entity = pastebinService.getPastebinLink("incorrect token", "correct content");
        String result = entity.getEntityInformation();
        String expected = "Error Message: Bad API request, invalid_api_dev_key\n";
        assertEquals(expected, result);
        assertEquals("ErrorInfo", entity.getEntityType());
    }

    @Test
    public void testSearchIrregularOnline() {
        PastebinService pastebinService = new PastebinService(onlinePastebinAPIStrategy);

        Entity entity = pastebinService.getPastebinLink("irregular token", "irregular content");
        String result = entity.getEntityInformation();
        String expected = "Error Message: Irregular response\n";
        assertEquals(expected, result);
        assertEquals("ErrorInfo", entity.getEntityType());
    }

    @Test
    public void testSearchIrregularOffline() {
        PastebinService pastebinService = new PastebinService(offlinePastebinAPIStrategy);

        Entity entity = pastebinService.getPastebinLink("irregular token", "irregular content");
        String result = entity.getEntityInformation();
        String expected = "Error Message: Irregular response\n";
        assertEquals(expected, result);
        assertEquals("ErrorInfo", entity.getEntityType());
    }
}
