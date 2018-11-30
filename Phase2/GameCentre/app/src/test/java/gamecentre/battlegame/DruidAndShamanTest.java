package gamecentre.battlegame;

import org.junit.Test;

import static org.junit.Assert.*;

public class DruidAndShamanTest {

    private BattleQueue bq = new BattleQueue();
    private Character player1;
    private Character player2;

    /**
     * Create a new battle queue with DruidShibe and its opponents to test attack functions.
     */
    private void setUpBattleQueue() {
        setUpCharacter();
        player1.setBattleQueue(bq);
        player2.setBattleQueue(bq);
        bq.add(player1);
        bq.add(player2);
    }

    /**
     * Set player1 and player2 as DruidShibe and ShamanCat to be used in the Battle Queue.
     */
    private void setUpCharacter() {
        player1 = new DruidShibe();
        player2 = new ShamanCat();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    /**
     * Test whether the battle queue is updated correctly after a regular move.
     */
    @Test
    public void testRegularMoveDruidVsShaman() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        assertEquals(92, bq.getNextCharacter().getHp());
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().regularMove();
        assertEquals(93, bq.getNextCharacter().getHp());
        assertEquals(player1, bq.getNextCharacter());
    }

    /**
     * Test whether the battle queue and character HP/MP is updated correctly after a special move.
     */
    @Test
    public void testSpecialMoveDruidVsShaman() {
        setUpBattleQueue();
        bq.getNextCharacter().specialMove();
        assertEquals(87, bq.getNextCharacter().getHp());
        assertEquals(90, bq.getNextCharacter().getOpponent().getMp());
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().specialMove();
        assertEquals(87, bq.getNextCharacter().getHp());
        assertEquals(78, bq.getNextCharacter().getOpponent().getMp());
        assertEquals(player1, bq.getNextCharacter());
    }

    /**
     * Test whether the correct sprite name is returned.
     */
    @Test
    public void testDruidVsShamanGetSprites() {
        setUpBattleQueue();
        String player1Sprite = bq.getNextCharacter().getSprite();
        assertEquals("druid_shibe", player1Sprite);
        String player2Sprite = bq.getNextCharacter().getOpponent().getSprite();
        assertEquals("shaman_cat", player2Sprite);
    }

    /**
     * Test whether the correct type is returned.
     */
    @Test
    public void testDruidVsShamanGetType() {
        setUpBattleQueue();
        String player1Type = bq.getNextCharacter().getType();
        assertEquals("dog", player1Type);
        String player2Type = bq.getNextCharacter().getOpponent().getType();
        assertEquals("cat", player2Type);
    }

    /**
     * Test whether the characters have MP.
     */
    @Test
    public void testDruidAndShamanHasMp() {
        setUpBattleQueue();
        assertTrue(bq.getNextCharacter().hasAttackMp());
        assertTrue(bq.getNextCharacter().getOpponent().hasAttackMp());
    }
}