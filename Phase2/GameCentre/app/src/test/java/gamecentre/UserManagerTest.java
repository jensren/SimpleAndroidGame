package gamecentre;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserManagerTest {

    private UserManager userManager = new UserManager();

    /**
     * Test whether UserManager correctly adds usernames and passwords.
     */
    @Test
    public void testUserManager(){
        userManager.addUser("and","rew");
        assertEquals("rew",userManager.getPassword("and"));
        userManager.addUser("a","a");
        assertEquals("a",userManager.getPassword("a"));
        assertEquals("rew",userManager.getPassword("and"));
    }
}
