package gamecentre;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserManagerTest {

    private UserManager userManager = new UserManager();

    @Test
    public void testUserManager(){
        userManager.addUser("and","rew");
        assertEquals("rew",userManager.getPassword("and"));
        userManager.addUser("a","a");
        assertEquals("a",userManager.getPassword("a"));
    }
}
