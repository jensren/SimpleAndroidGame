package gamecentre.battlegame;

import org.junit.Test;

import static org.junit.Assert.*;

public class SirVsSamuraiTest {

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
        player1 = new SirShibe();
        player2 = new SamuraiCat();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    @Test
    public void testRegularMoveSirVsSamurai() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().regularMove();
        assertEquals(player1, bq.getNextCharacter());
    }

    @Test
    public void testSpecialMoveSirVsSamurai() {
        setUpBattleQueue();
        bq.getNextCharacter().specialMove();
        assertEquals(player2, bq.getNextCharacter());
        bq.getNextCharacter().specialMove();
        //assertEquals(player2, bq.getNextCharacter());
        assertEquals(player1, bq.getNextCharacter());
    }

    @Test
    public void testSirVsSamuraiGetSprites() {
        setUpBattleQueue();
        String player1Sprite = bq.getNextCharacter().getSprite();
        assertEquals("sir_shibe", player1Sprite);
        String player2Sprite = bq.getNextCharacter().getOpponent().getSprite();
        assertEquals("samurai_cat", player2Sprite);
    }

    @Test
    public void testSirVsSamuraiGetType() {
        setUpBattleQueue();
        String player1Type = bq.getNextCharacter().getType();
        assertEquals("dog", player1Type);
        String player2Type = bq.getNextCharacter().getOpponent().getType();
        assertEquals("cat", player2Type);
    }

    @Test
    public void testSirVsSamuraiHasMp() {
        setUpBattleQueue();
        assertTrue(bq.getNextCharacter().hasAttackMp());
        assertTrue(bq.getNextCharacter().getOpponent().hasAttackMp());
    }
}