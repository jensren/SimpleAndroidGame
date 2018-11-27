package gamecentre.battlegame;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

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

    @Test
    public void testRegularMoveDruidVsShaman() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().regularMove();
        assertEquals(player1, bq.getNextCharacter());
    }

    @Test
    public void testSpecialMoveDruidVsShaman() {
        setUpBattleQueue();
        bq.getNextCharacter().specialMove();
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().specialMove();
        assertEquals(player1, bq.getNextCharacter());
        assertEquals(player1, bq.getNextCharacter());
    }

    @Test
    public void testDruidVsShamanGetSprites() {
        setUpBattleQueue();
        String player1Sprite = bq.getNextCharacter().getSprite();
        assertEquals("druid_shibe", player1Sprite);
        String player2Sprite = bq.getNextCharacter().getOpponent().getSprite();
        assertEquals("shaman_cat", player2Sprite);
    }

    @Test
    public void testDruidVsShamanGetType() {
        setUpBattleQueue();
        String player1Type = bq.getNextCharacter().getType();
        assertEquals("dog", player1Type);
        String player2Type = bq.getNextCharacter().getOpponent().getType();
        assertEquals("cat", player2Type);
    }

    @Test
    public void testDruidAndShamanHasMp() {
        setUpBattleQueue();
        assertTrue(bq.getNextCharacter().hasAttackMp());
        assertTrue(bq.getNextCharacter().getOpponent().hasAttackMp());
    }
}