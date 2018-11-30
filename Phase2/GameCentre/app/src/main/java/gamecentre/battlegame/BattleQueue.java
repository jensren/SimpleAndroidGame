package gamecentre.battlegame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The battle queue.
 */
public class BattleQueue implements Serializable {

    /**
     * Player 1
     */
    private Character player1;
    /**
     * Player 2
     */
    private Character player2;
    /**
     * The number of moves made
     */
    private int numMoves;
    /**
     * The queue for the game
     */
    private List<Character> queue = new ArrayList<>();
    /**
     * A stack of moves
     */
    private List<BattleQueue> undoStack = new ArrayList<>();
    /**
     * A stack of player attributes after each turn
     */
    private List<int[]> playerAttributesStack = new ArrayList<>();

    /**
     * Add a character to the end of the battle queue.
     *
     * @param character The character to add.
     */
    void add(Character character) {
        if (player1 == null) {
            player1 = character;
            player2 = character.getOpponent();
        }
        queue.add(character);
    }

    /**
     * Count how many total moves are made in this battle queue.
     */
    void makeMove() {
        numMoves++;
    }

    /**
     * Return the total number of moves performed in the battle queue.
     * @return Total number of moves performed in this battle queue.
     */
    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Return player1
     *
     * @return player1
     */
    Character getPlayer1() {
        return player1;
    }

    /**
     * Return player2
     *
     * @return player2
     */
    Character getPlayer2() {
        return player2;
    }

    /**
     * Remove and return the next character from the front of the battle queue.
     *
     */
    void removeCharacter() {
        if (queue.size() > 0) {
            queue.remove(0);
        }
    }

    /**
     * Return the next character to make a move.
     *
     * @return the next character.
     */
    Character getNextCharacter() {
        if (queue.size() > 0 ) {
            return queue.get(0);
        } else return player1;
    }

    /**
     * Return the winner of the game, if there is one. If there is no winner, return null.
     *
     * @return the winner of the game if there is one, null otherwise.
     */
    Character getWinner() {
        int initialHP = Character.getInitialHp();
        int player1HP = player1.getHp();
        int player2HP = player2.getHp();
        BattleScoreboard.setPlayer1HpLost(initialHP - player1HP);
        BattleScoreboard.setPlayer2HpLost(initialHP - player2HP);
        if (player1HP > 0 && player2HP == 0) {
            BattleScoreboard.setNumMoves(numMoves);
            return player1;
        } else if (player1HP == 0 && player2HP > 0) {
            BattleScoreboard.setNumMoves(numMoves);
            return player2;
        } else {
            return null;
        }
    }

    /**
     * Return whether or not this Battle Queue is empty.
     * @return true if this Battle Queue is empty, false otherwise.
     */
    boolean isEmpty() {
        return queue.size() == 0;
    }

    /**
     * Create and return a copy of this battle queue which contains all the characters in
     * the same order.
     * @return A copy of this battle queue.
     */
    BattleQueue copyBq() {
        BattleQueue bq = new BattleQueue();
        for (Character ch : queue) {
            bq.add(ch);
        }
        return bq;
    }

    /**
     * Update the undoStack by adding in a cpy of the battle queue bq
     * @param bq The battle queue to add.
     */
    void updateUndoStack(BattleQueue bq) {
        undoStack.add(bq);
    }

    /**
     * Store this character's HP, MP and this character's
     *
     * @param ch first character in the battle queue before attack is performed.
     */
    void updatePlayerAttributesStack(Character ch) {
        int[] attributeArray = new int[4];
        attributeArray[0] = ch.getHp();
        attributeArray[1] = ch.getMp();
        attributeArray[2] = ch.getOpponent().getHp();
        attributeArray[3] = ch.getOpponent().getMp();
        playerAttributesStack.add(attributeArray);
    }

    /**
     * Return if the click to the undo button is invalid
     * @return True if there are no moves to undo, else return false.
     */
    boolean isInValidUndo() {
        return undoStack.size() == 0 || playerAttributesStack.size() == 0;
    }

    /**
     * Undo the last move made in this Battle Queue.
     */
    public void undo() {
        if (undoStack.size() > 0) {
            makeMove();
            BattleQueue bq = undoStack.remove(undoStack.size() - 1);
            while (!isEmpty()) {
                removeCharacter();
            }
            queue.addAll(bq.queue);
            if (getNextCharacter() == player1) {
                if (playerAttributesStack.size() > 0) {
                    int[] attributes = playerAttributesStack.remove(playerAttributesStack.size() - 1);
                    player1.setHp(attributes[0]);
                    player1.setMp(attributes[1]);
                    player2.setHp(attributes[2]);
                    player2.setMp(attributes[3]);
                } else {
                    throw new IndexOutOfBoundsException("Attribute stack empty!");
                }
            } else {
                int[] attributes = playerAttributesStack.remove(playerAttributesStack.size() - 1);
                player2.setHp(attributes[0]);
                player2.setMp(attributes[1]);
                player1.setHp(attributes[2]);
                player1.setMp(attributes[3]);
            }
        }
    }
}
