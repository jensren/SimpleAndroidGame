package battlegame;

import java.util.ArrayList;

public class BattleQueue {

    private ArrayList<Character> queue = new ArrayList<>();

    /**
     * Add a character to the end of the battle queue.
     *
     * @param character The character to add.
     */
    void add(Character character) {
        queue.add(character);
    }

    private void removeInvalidCharacters() {
        while (!getNextCharacter().hasAttackMp()) {
            remove();
        }
    }

    /**
     * Remove the next character from the front of the battle queue.
     */
    private void remove() {
        queue.remove(0);
    }

    /**
     * Return whether the game is over (i.e. there are no more moves left).
     *
     * @return true iff the game is over.
     */
    boolean isOver() {
        return queue.isEmpty();
    }

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
        return null;
    }

    @Override
    public String toString() {
        return "";
    }


}
