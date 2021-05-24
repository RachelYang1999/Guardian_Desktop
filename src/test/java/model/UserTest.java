package model;

import model.domain.Entity;
import model.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testGetSetTokenValid() {
        User user = new User();
        user.setToken("token");
        assertEquals("token", user.getToken());
    }

    @Test
    public void testGetSetLoginStatusValid() {
        User user = new User();
        user.setLoginStatus(true);
        assertTrue(user.isLoginStatus());
    }

    @Test
    public void testGetSetUserTierValid() {
        User user = new User();
        user.setUserTier("user tier");
        assertEquals("user tier", user.getUserTier());
    }

    @Test
    public void testGetEntityTypeValid() {
        User user = new User();
        assertEquals("User", user.getEntityType());
    }

    @Test
    public void testGetEntityInfoValid() {
        User user = new User();
        user.setUserTier("developer");
        user.setLoginStatus(true);

        String expected = "User Tier: developer" + "\n" +
                          "Login Status: Logged In" + "\n";
        assertEquals(expected, user.getEntityInformation());
    }

    @Test
    public void testGetEntityTypeViaInterfaceValid() {
        Entity user = new User();
        assertEquals("User", user.getEntityType());
    }

    @Test
    public void testGetEntityInfoViaInterfaceValid() {
        User user = new User();
        user.setUserTier("developer");
        user.setLoginStatus(true);

        String expected = "User Tier: developer" + "\n" +
                "Login Status: Logged In" + "\n";
        Entity userEntityType = user;
        assertEquals(expected, userEntityType.getEntityInformation());
    }
}
