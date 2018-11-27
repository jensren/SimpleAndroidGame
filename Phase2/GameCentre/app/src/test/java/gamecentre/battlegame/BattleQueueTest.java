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

//    @Test
//    public void add() {
//    }
//
//    @Test
//    public void removeCharacter() {
//    }

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

//    @Test
//    public void updateUndoStack() {
//    }
//
//    @Test
//    public void updatePlayerAttributesStack() {
//    }
//
//    @Test
//    public void undo() {
//    }
}