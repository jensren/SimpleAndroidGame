package gamecentre.battlegame;

import java.io.Serializable;
import java.util.ArrayList;

public class BattleQueue implements Serializable {

    private Character player1;
    private Character player2;

    private ArrayList<Character> queue = new ArrayList<>();

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
     * Create and return a copy of this battle queue which contains copies of all the characters in
     * the same order.
     * precondition: Assume there is at least one character in the battle queue.
     * @return A copy of this battle queue.
     */
    public BattleQueue copyBq() {
        BattleQueue bq = new BattleQueue();
        Character p1Copy = player1.copyCharacter();
        Character p2Copy = player2.copyCharacter();

        bq.add(p1Copy);
        if (!bq.isEmpty()) {
            bq.removeCharacter();
        }
        for (Character ch : queue) {
            if (ch == player1) {
                bq.add(p1Copy);
            } else { bq.add(p2Copy); }
        }

        return bq;
    }

}
