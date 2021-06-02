package model.service;

import model.dao.ArticleDao;
import model.dao.DaoUtil;
import model.dao.UserDao;
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

import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/*
According to the specification, we need to mock all external dependencies, in this case,
I mocked the access to both online and offline API to test the model service
 */

public class UserServiceTest {

  private GuardianAPIStrategy onlineGuardianAPIStrategy;
  private GuardianAPIStrategy offlineGuardianAPIStrategy;

  private UserDao userDao;

  @Before
  public void init() {
    String dummyResponseSuccessful =
        "{\"response\":{\"status\": \"ok\",\"userTier\":\"developer\",\"total\":63957,\"startIndex\":1,\"pages\":6396,\"pageSize\":10,\"currentPage\":1}}";
    String dummyResponseUnsuccessful = "{\"message\":\"Invalid authentication credentials\"}";
    String dummyResponseIrregular = "{\"idk\":\"irregular response\"}";

    JSONObject successfulResponse = new JSONObject(dummyResponseSuccessful);
    JSONObject unsuccessfulResponse = new JSONObject(dummyResponseUnsuccessful);
    JSONObject irregularResponse = new JSONObject(dummyResponseIrregular);

    onlineGuardianAPIStrategy = Mockito.mock(GuardianOnlineAPIStrategy.class);
    offlineGuardianAPIStrategy = Mockito.mock(GuardianOfflineAPIStrategy.class);

    userDao = Mockito.mock(UserDao.class);

    when(onlineGuardianAPIStrategy.login("correct token")).thenReturn(successfulResponse);
    when(offlineGuardianAPIStrategy.login("correct token")).thenReturn(successfulResponse);

    when(onlineGuardianAPIStrategy.login("incorrect token")).thenReturn(unsuccessfulResponse);
    when(offlineGuardianAPIStrategy.login("incorrect token")).thenReturn(unsuccessfulResponse);

    when(onlineGuardianAPIStrategy.login("irregular")).thenReturn(irregularResponse);
    when(offlineGuardianAPIStrategy.login("irregular")).thenReturn(irregularResponse);

    List<String> resultList = new ArrayList<>();
    resultList.add(("exist info"));
    when(userDao.getEntity(anyString(), anyString(), anyString())).thenReturn(resultList);

    when(userDao.addEntity(any())).thenReturn(true);
  }

  @Test
  public void testLoginSuccessOnline() {
    UserService userService = new UserService(onlineGuardianAPIStrategy, userDao);
    Entity returnedEntity = userService.login("correct token");
    String expected = "User Tier: developer" + "\n" + "Login Status: Logged In" + "\n";
    assertEquals("User", returnedEntity.getEntityType());
    assertEquals(expected, returnedEntity.getEntityInformation());
  }

  @Test
  public void testLoginSuccessOffline() {
    UserService userService = new UserService(offlineGuardianAPIStrategy, userDao);
    Entity returnedEntity = userService.login("correct token");
    String expected = "User Tier: developer" + "\n" + "Login Status: Logged In" + "\n";
    assertEquals("User", returnedEntity.getEntityType());
    assertEquals(expected, returnedEntity.getEntityInformation());
  }

  @Test
  public void testLoginUnsuccessfulOnline() {
    UserService userService = new UserService(onlineGuardianAPIStrategy, userDao);
    Entity returnedEntity = userService.login("incorrect token");
    String expected = "Error Message: Invalid authentication credentials" + "\n";
    assertEquals("ErrorInfo", returnedEntity.getEntityType());
    assertEquals(expected, returnedEntity.getEntityInformation());
  }

  @Test
  public void testLoginUnsuccessfulOffline() {
    UserService userService = new UserService(offlineGuardianAPIStrategy, userDao);
    Entity returnedEntity = userService.login("incorrect token");
    String expected = "Error Message: Invalid authentication credentials" + "\n";
    assertEquals("ErrorInfo", returnedEntity.getEntityType());
    assertEquals(expected, returnedEntity.getEntityInformation());
  }

  @Test
  public void testLoginIrregularOnline() {
    UserService userService = new UserService(onlineGuardianAPIStrategy, userDao);
    Entity returnedEntity = userService.login("irregular");
    String expected = "Error Message: Unknown Error!" + "\n";
    assertEquals("ErrorInfo", returnedEntity.getEntityType());
    assertEquals(expected, returnedEntity.getEntityInformation());
  }

  @Test
  public void testLoginIrregularOffline() {
    UserService userService = new UserService(offlineGuardianAPIStrategy, userDao);
    Entity returnedEntity = userService.login("irregular");
    String expected = "Error Message: Unknown Error!" + "\n";
    assertEquals("ErrorInfo", returnedEntity.getEntityType());
    assertEquals(expected, returnedEntity.getEntityInformation());
  }
}
