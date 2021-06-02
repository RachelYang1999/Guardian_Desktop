package model.domain;

import model.domain.Entity;
import model.domain.ErrorInfo;
import model.domain.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResponseTest {

  @Test
  public void testGetSetErrorCodeValid() {
    Response response = new Response();
    response.setInfo("This is a response");
    assertEquals("This is a response", response.getInfo());
  }

  @Test
  public void testGetEntityTypeValid() {
    Response response = new Response();
    assertEquals("Response", response.getEntityType());
  }

  @Test
  public void testGetEntityInfoValid() {
    Response response = new Response();
    response.setInfo("This is a response");
    String expected = "Response Information: This is a response";
    assertEquals(expected, response.getEntityInformation());
  }

  @Test
  public void testGetEntityTypeViaInterfaceValid() {
    Entity response = new Response();
    assertEquals("Response", response.getEntityType());
  }

  @Test
  public void testGetEntityInfoViaInterfaceValid() {
    Response response = new Response();
    response.setInfo("This is a response");
    String expected = "Response Information: This is a response";

    Entity responseEntity = response;
    assertEquals(expected, responseEntity.getEntityInformation());
  }
}
