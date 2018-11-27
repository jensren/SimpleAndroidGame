package gamecentre.battlegame;

/**
 * Manager for the battle queue.
 */
class BattleQueueManager {

    /**
     * The battle queue being managed.
     */
    private BattleQueue battleQueue;

    /**
     * Player 1 in the game
     */
    private Character player1;

    /**
     * Player 2 in the game
     */
    private Character player2;

    /**
     * Return the battle queue
     *
     * @return the battle queue
     */
    BattleQueue getBattleQueue() {
        return battleQueue;
    }

    /**
     * Return player 1
     *
     * @return player1
     */
    Character getPlayer1() {
        return player1;
    }

    /**
     * Return player 2
     *
     * @return player2
     */
    Character getPlayer2() {
        return player2;
    }

    BattleQueueManager() {
        battleQueue = new BattleQueue();
    }

    /**
     * Set the opponents and battle queues of player1 and player2, and add them to the battle queue.
     */
    void initializeBattleQueue() {
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        player1.setBattleQueue(battleQueue);
        player2.setBattleQueue(battleQueue);
        battleQueue.add(player1);
        battleQueue.add(player2);
    }

    /**
     * Set player 1 and player 2's characters
     *
     * @param p1Class player 1's class
     * @param p2Class player2's class
     */
    void setCharacters(String p1Class, String p2Class) {
        switch (p1Class) {
            case "NinjaCat":
                player1 = new NinjaCat();
                break;
            case "SamuraiCat":
                player1 = new SamuraiCat();
                break;
            case "ShamanCat":
                player1 = new ShamanCat();
                break;
            case "DetectiveShibe":
                player1 = new DetectiveShibe();
                break;
            case "SirShibe":
                player1 = new SirShibe();
                break;
            case "DruidShibe":
                player1 = new DruidShibe();
                break;
        }
        switch (p2Class) {
            case "NinjaCat":
                player2 = new NinjaCat();
                break;
            case "SamuraiCat":
                player2 = new SamuraiCat();
                break;
            case "ShamanCat":
                player2 = new ShamanCat();
                break;
            case "DetectiveShibe":
                player2 = new DetectiveShibe();
                break;
            case "SirShibe":
                player2 = new SirShibe();
                break;
            case "DruidShibe":
                player2 = new DruidShibe();
                break;
        }
    }
}
