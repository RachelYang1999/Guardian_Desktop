import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testConstructorValid() {
        User user = new User();
        assertNotNull(user);
    }

}
