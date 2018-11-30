package gamecentre.battlegame;

import org.junit.Test;

import static org.junit.Assert.*;

public class BattleQueueManagerTest {

    /**
     * The battle queue manager for testing
     */
    private BattleQueueManager battleQueueManager = new BattleQueueManager();

    /**
     * Test whether the battle queue is correctly initialized.
     */
    @Test
    public void testInitializeBattleQueue() {
        battleQueueManager.setCharacters("NinjaCat", "DetectiveShibe");
        battleQueueManager.initializeBattleQueue();
        Character player1 = battleQueueManager.getPlayer1();
        Character player2 = battleQueueManager.getPlayer2();
        assertEquals("DetectiveShibe", player1.getOpponent().getClass().getSimpleName());
        assertEquals("NinjaCat", player2.getOpponent().getClass().getSimpleName());
        BattleQueue battleQueue1 = battleQueueManager.getBattleQueue();
        BattleQueue battleQueue2 = battleQueueManager.getBattleQueue();
        assertEquals(battleQueue1, battleQueue2);
    }

    /**
     * Test whether the characters are set properly.
     */
    @Test
    public void testSetCharacters() {
        battleQueueManager.setCharacters("DetectiveShibe", "NinjaCat");
        Character player1 = battleQueueManager.getPlayer1();
        Character player2 = battleQueueManager.getPlayer2();
        assertEquals("DetectiveShibe", player1.getClass().getSimpleName());
        assertEquals("NinjaCat", player2.getClass().getSimpleName());

        battleQueueManager.setCharacters("DruidShibe", "SamuraiCat");
        player1 = battleQueueManager.getPlayer1();
        player2 = battleQueueManager.getPlayer2();
        assertEquals("DruidShibe", player1.getClass().getSimpleName());
        assertEquals("SamuraiCat", player2.getClass().getSimpleName());

        battleQueueManager.setCharacters("SamuraiCat", "SirShibe");
        player1 = battleQueueManager.getPlayer1();
        player2 = battleQueueManager.getPlayer2();
        assertEquals("SamuraiCat", player1.getClass().getSimpleName());
        assertEquals("SirShibe", player2.getClass().getSimpleName());

        battleQueueManager.setCharacters("SirShibe", "ShamanCat");
        player1 = battleQueueManager.getPlayer1();
        player2 = battleQueueManager.getPlayer2();
        assertEquals("SirShibe", player1.getClass().getSimpleName());
        assertEquals("ShamanCat", player2.getClass().getSimpleName());

        battleQueueManager.setCharacters("ShamanCat", "DruidShibe");
        player1 = battleQueueManager.getPlayer1();
        player2 = battleQueueManager.getPlayer2();
        assertEquals("ShamanCat", player1.getClass().getSimpleName());
        assertEquals("DruidShibe", player2.getClass().getSimpleName());
    }
}