package model;

import model.domain.Entity;
import model.domain.ErrorInfo;
import model.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorInfoTest {

    @Test
    public void testConstructorValid() {
        ErrorInfo errorInfo = new ErrorInfo();
        assertNotNull(errorInfo);
    }

    @Test
    public void testGetSetErrorCodeValid() {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(404);
        assertEquals(404, errorInfo.getErrorCode());
    }

    @Test
    public void testGetSetMessageValid() {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("File Not Found");
        assertEquals("File Not Found", errorInfo.getMessage());
    }

    @Test
    public void testGetSetRelatedModuleValid() {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setRelatedModule("User");
        assertEquals("User", errorInfo.getRelatedModule());
    }

    @Test
    public void testGetEntityTypeValid() {
        Entity errorInfo = new ErrorInfo();
        assertEquals("ErrorInfo", errorInfo.getEntityType());
    }

    @Test
    public void testGetEntityInfoValid() {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("File Not Found");
        errorInfo.setErrorCode(404);
        errorInfo.setRelatedModule("User");

        String expected = "Error Message: File Not Found"+ "\n"
                        + "Error Code: 404" + "\n"
                        + "Related Module: User" + "\n";
        assertEquals(expected, errorInfo.getEntityInformation());
    }

    @Test
    public void testGetEntityTypeViaInterfaceValid() {
        Entity errorInfo = new ErrorInfo();
        assertEquals("ErrorInfo", errorInfo.getEntityType());
    }

    @Test
    public void testGetEntityInfoViaInterfaceValid() {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage("File Not Found");
        errorInfo.setErrorCode(404);
        errorInfo.setRelatedModule("User");

        String expected = "Error Message: File Not Found"+ "\n"
                + "Error Code: 404" + "\n"
                + "Related Module: User" + "\n";
        Entity userEntityType = errorInfo;
        assertEquals(expected, userEntityType.getEntityInformation());
    }
}
