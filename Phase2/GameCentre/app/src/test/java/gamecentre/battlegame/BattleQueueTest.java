package gamecentre.battlegame;

import org.junit.Test;

import static org.junit.Assert.*;

public class BattleQueueTest {

    private BattleQueue bq = new BattleQueue();
    private Character player1;
    private Character player2;

    /**
     * Create a new battle queue with 2 characters as opponents to test battle queue functions.
     */
    private void setUpBattleQueue() {
        setUpCharacter();
        player1.setBattleQueue(bq);
        player2.setBattleQueue(bq);
        bq.add(player1);
        bq.add(player2);

    }

    /**
     * Set player1 and player2 as NinjaCat and SirShibe to be used in the Battle Queue.
     */
    private void setUpCharacter() {
        player1 = new NinjaCat();
        player2 = new SirShibe();
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }


    @Test
    public void testGetNextCharacterEmptyBattleQueue() {
        assertTrue(bq.isEmpty());
        Character nextCharacter = bq.getNextCharacter();
        assertNull(nextCharacter);
    }

    @Test
    public void testGetNextCharacterWithCharacters() {
        setUpBattleQueue();
        Character nextCharacter = bq.getNextCharacter();
        assertEquals(player1, nextCharacter);
    }

    @Test
    public void TestGetWinnerNoWinner() {
        setUpBattleQueue();
        Character winner = bq.getWinner();
        assertNull(winner);

    }

    @Test
    public void testGetPlayers() {
        setUpBattleQueue();
        assertEquals(player1, bq.getPlayer1());
        bq.getNextCharacter().regularMove();
        assertEquals(player2, bq.getPlayer2());
    }

    @Test
    public void testWinner() {
        setUpBattleQueue();
        player1.setHp(0);
        assertEquals(player2, bq.getWinner());
        player1.setHp(100);
        player2.setHp(0);
        assertEquals(player1, bq.getWinner());
    }

    @Test
    public void testInValidUndo() {
        setUpBattleQueue();
        assertTrue(bq.isInValidUndo());
        assertEquals(0, bq.getNumMoves());
    }

    @Test
    public void testPlayer1Undo() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        bq.undo();
        assertEquals(player1, bq.getNextCharacter());
        assertEquals(100, player1.getHp());
        bq.getNextCharacter().regularMove();
        bq.getNextCharacter().regularMove();
        bq.undo();
        assertEquals(100, player1.getMp());

    }

}