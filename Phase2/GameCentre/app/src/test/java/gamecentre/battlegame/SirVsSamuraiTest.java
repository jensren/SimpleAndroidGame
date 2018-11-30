package gamecentre.battlegame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unittest class to test the functions of SirShibe and SamuraiCat characters in a BattleQueue.
 */
public class SirVsSamuraiTest {

    /**
     * The battle queue for testing
     */
    private BattleQueue bq = new BattleQueue();
    /**
     * Player 1
     */
    private Character player1;
    /**
     * Player 2
     */
    private Character player2;

    /**
     * Create a new battle queue with SirShibe and its opponents to test attack functions.
     */
    @Before
    public void setUpBattleQueue() {
        setUpCharacter();
        player1.setBattleQueue(bq);
        player2.setBattleQueue(bq);
        bq.add(player1);
        bq.add(player2);
    }

    /**
     * Set player1 and player2 as SirShibe and SamuraiCat to be used in the Battle Queue.
     */
    @Before
    public void setUpCharacter() {
        player1 = new SirShibe();
        player2 = new SamuraiCat();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    /**
     * Test whether the BattleQueue and character Hp/Mp are updated correctly after performing a
     * regular move.
     */
    @Test
    public void testRegularMoveSirVsSamurai() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        assertEquals(90, bq.getNextCharacter().getHp());
        assertEquals(bq.getPlayer2(), bq.getNextCharacter());
        bq.getNextCharacter().regularMove();
        assertEquals(100, bq.getNextCharacter().getHp());
        assertEquals(player1, bq.getNextCharacter());
    }

    /**
     * Test specialMove on SirShibe and SamuraiCat to make sure the BattleQueue and character
     * attributes are updated properly.
     */
    @Test
    public void testSpecialMoveSirVsSamurai() {
        setUpBattleQueue();
        bq.getNextCharacter().specialMove();
        assertEquals(87, bq.getNextCharacter().getHp());
        assertEquals(88, bq.getNextCharacter().getOpponent().getMp());
        assertEquals(bq.getPlayer2(), bq.getNextCharacter());
        bq.getNextCharacter().specialMove();
        assertEquals(85, bq.getNextCharacter().getHp());
        assertEquals(87, bq.getNextCharacter().getOpponent().getHp());
        assertEquals(bq.getPlayer1(), bq.getNextCharacter());
    }

    /**
     * Test whether the correct sprite is returned.
     */
    @Test
    public void testSirVsSamuraiGetSprites() {
        setUpBattleQueue();
        String player1Sprite = bq.getNextCharacter().getSprite();
        assertEquals("sir_shibe", player1Sprite);
        String player2Sprite = bq.getNextCharacter().getOpponent().getSprite();
        assertEquals("samurai_cat", player2Sprite);
    }

    /**
     * Test whether the correct type is returned.
     */
    @Test
    public void testSirVsSamuraiGetType() {
        setUpBattleQueue();
        String player1Type = bq.getNextCharacter().getType();
        assertEquals("dog", player1Type);
        String player2Type = bq.getNextCharacter().getOpponent().getType();
        assertEquals("cat", player2Type);
    }

    /**
     * Test whether the the characters have Mp.
     */
    @Test
    public void testSirVsSamuraiHasMp() {
        setUpBattleQueue();
        assertTrue(bq.getNextCharacter().hasAttackMp());
        assertTrue(bq.getNextCharacter().getOpponent().hasAttackMp());
    }
}