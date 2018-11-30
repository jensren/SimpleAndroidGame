package gamecentre.battlegame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unittest class to test the functions of DetectiveShibe and NinjaCat characters in a BattleQueue.
 */
public class DetectiveVsNinjaTest {

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
     * Create a new battle queue with Detective Shibe and its opponents to test attack functions.
     */
    private void setUpBattleQueue() {
        setUpCharacter();
        player1.setBattleQueue(bq);
        player2.setBattleQueue(bq);
        bq.add(player1);
        bq.add(player2);
    }

    /**
     * Set player1 and player2 as DetectiveShibe and NinjaCat to be used in the Battle Queue.
     */
    private void setUpCharacter() {
        player1 = new DetectiveShibe();
        player2 = new NinjaCat();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    /**
     * Test regularMove of DetectiveShibe and NinjaCat to make sure the opponents take the
     * appropriate damage and the BattleQueue is in the correct order after each move.
     */
    @Test
    public void testRegularMoveDetectiveVsNinja() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        assertEquals(player2, bq.getNextCharacter());
        assertEquals(93, bq.getNextCharacter().getHp());
        bq.getNextCharacter().regularMove();
        assertEquals(player1, bq.getNextCharacter());
        assertEquals(95, bq.getNextCharacter().getHp());
    }

    /**
     * Test specialMove of DetectiveShibe and NinjaCat to make sure the opponents take the
     * appropriate damage and the BattleQueue is in the correct order after each move
     */
    @Test
    public void testSpecialMoveDetectiveVsNinja() {
        setUpBattleQueue();
        bq.getNextCharacter().specialMove();
        assertEquals(88, bq.getNextCharacter().getHp());
        assertEquals(85, bq.getNextCharacter().getOpponent().getMp());
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().specialMove();
        assertEquals(91, bq.getNextCharacter().getMp());
        assertEquals(85, bq.getNextCharacter().getOpponent().getMp());
        assertEquals(player2, bq.getNextCharacter());
    }

    /**
     * Test whether the correct sprite name is returned.
     */
    @Test
    public void testDetectiveVsNinjaGetSprites() {
        setUpBattleQueue();
        String player1Sprite = bq.getNextCharacter().getSprite();
        assertEquals("detective_shibe", player1Sprite);
        String player2Sprite = bq.getNextCharacter().getOpponent().getSprite();
        assertEquals("ninja_cat", player2Sprite);
    }

    /**
     * Test whether the correct type is returned.
     */
    @Test
    public void testDetectiveVsNinjaGetType() {
        setUpBattleQueue();
        String player1Type = bq.getNextCharacter().getType();
        assertEquals("dog", player1Type);
        String player2Type = bq.getNextCharacter().getOpponent().getType();
        assertEquals("cat", player2Type);
    }

    /**
     * Test HasMp method on DetectiveShibe and NinjaCat after the BattleQueue has characters in it.
     */
    @Test
    public void testDetectiveVsNinjaHasMp() {
        setUpBattleQueue();
        assertTrue(bq.getNextCharacter().hasAttackMp());
        assertTrue(bq.getNextCharacter().getOpponent().hasAttackMp());
    }

    /**
     * Test setHp and setMp for DetectiveShibe and NinjaCat.
     */
    @Test
    public void testReducedHpAndMp() {
        setUpBattleQueue();
        bq.getNextCharacter().setHp(0);
        bq.getNextCharacter().getOpponent().setHp(0);
        assertEquals(0, bq.getNextCharacter().getHp());
        assertEquals(0, bq.getNextCharacter().getOpponent().getHp());
        assertEquals(100, Character.getInitialHp());
        bq.getNextCharacter().setMp(0);
        assertEquals(0, bq.getNextCharacter().getMp());
    }
}