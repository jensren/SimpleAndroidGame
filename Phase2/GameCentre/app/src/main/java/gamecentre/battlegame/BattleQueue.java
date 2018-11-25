package gamecentre.battlegame;

import java.io.Serializable;
import java.util.ArrayList;

public class BattleQueue implements Serializable {

    private Character player1;
    private Character player2;

    private ArrayList<Character> queue = new ArrayList<>();
    private ArrayList<BattleQueue> undoStack = new ArrayList<>();

    BattleQueue() {

    }

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

    private void removeInvalidCharacters() {
        while (!getNextCharacter().hasAttackMp()) {
            removeCharacter();
        }
    }

    /**
     * Remove and return the next character from the front of the battle queue.
     *
     * @Return The rext character in this Battle Queue
     */
    Character removeCharacter() {
        return queue.remove(0);
    }

//    /**
//     * Return whether the game is over (i.e. there are no more moves left).
//     *
//     * @return true iff the game is over.
//     */
//    boolean isOver() {
//        return queue.isEmpty();
//    }

    /**
     * Return the next character to make a move.
     *
     * @return the next character.
     */
    Character getNextCharacter() {
        return queue.get(0);
    }

    /**
     * Return the winner of the game, if there is one. If there is no winner, return null.
     *
     * @return the winner of the game if there is one, null otherwise.
     */
    Character getWinner() {

        if (player1.getHp() > 0 && player2.getHp() == 0) {
            return player1;
        } else if (player1.getHp() == 0 && player2.getHp() > 0) {
            return player2;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "";
    }

    /**
     * Return whether or not this Battle Queue is empty.
     * @return true if this Battle Queue is empty, false otherwise.
     */
    boolean isEmpty() {
        removeInvalidCharacters();
        return queue.size() == 0;
    }

    /**
     * Create and return a copy of this battle queue which contains all the characters in
     * the same order.
     * @return A copy of this battle queue.
     */
    public BattleQueue copyBq() {
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
    public void updateUndoStack(BattleQueue bq) {
        undoStack.add(bq);
    }

    /**
     * Undo the last move made in this Battle Queue.
     */
    public void undo() {
        if (undoStack.size() > 0) {
            BattleQueue bq = undoStack.get(undoStack.size() - 1);
            while (!isEmpty()) {
                removeCharacter();
            }
            for (Character ch : bq.queue) {
                this.add(ch);
            }
        }
    }

}
