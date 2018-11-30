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

    /**
     * Test to get the next character in an empty battle queue.
     */
    @Test
    public void testGetNextCharacterEmptyBattleQueue() {
        assertTrue(bq.isEmpty());
        Character nextCharacter = bq.getNextCharacter();
        assertNull(nextCharacter);
    }

    /**
     * Test to get the next character in a battle queue before any moves are performed.
     */
    @Test
    public void testGetNextCharacterWithCharacters() {
        setUpBattleQueue();
        Character nextCharacter = bq.getNextCharacter();
        assertEquals(player1, nextCharacter);
    }

    /**
     * Test getWinner in a battle queue with no winner.
     */
    @Test
    public void TestGetWinnerNoWinner() {
        setUpBattleQueue();
        Character winner = bq.getWinner();
        assertNull(winner);

    }

    /**
     * Test to make sure the getters for player1 and player2 are returning the corresponding
     * characters.
     */
    @Test
    public void testGetPlayers() {
        setUpBattleQueue();
        assertEquals(player1, bq.getPlayer1());
        bq.getNextCharacter().regularMove();
        assertEquals(player2, bq.getPlayer2());
    }

    /**
     * Test getWinner when one player1 has 0 HP and when player2 has 0 HP.
     */
    @Test
    public void testGetWinner() {
        setUpBattleQueue();
        player1.setHp(0);
        assertEquals(player2, bq.getWinner());
        player1.setHp(100);
        player2.setHp(0);
        assertEquals(player1, bq.getWinner());
    }

    /**
     * Test IsValidUndo on a BattleQueue that doesn't have any moves performed in it.
     */
    @Test
    public void testInValidUndo() {
        setUpBattleQueue();
        assertTrue(bq.isInValidUndo());
        assertEquals(0, bq.getNumMoves());
    }

    /**
     * Test undo function when a player performs a regular move. The attributes should be set to
     * what they were before the move was performed.
     */
    @Test
    public void testPlayerUndo() {
        setUpBattleQueue();
        bq.getNextCharacter().regularMove();
        bq.undo();
        assertEquals(player1, bq.getNextCharacter());
        assertEquals(100, player2.getHp());
        bq.getNextCharacter().regularMove();
        bq.getNextCharacter().regularMove();
        bq.undo();
        assertEquals(100, player1.getMp());

    }

}