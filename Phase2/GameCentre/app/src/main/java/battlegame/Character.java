package battlegame;

abstract class Character {
    // character's special attack deflects opponent's move back at them

    private int hp = 100;
    private int mp = 100;
    private Character opponent;


    boolean hasMp() {
        return true;
    }

    int getMp() {
        return mp;
    }

    int getHp() {
        return hp;

    }

    /**
     * Get an Array of the actions available for this character.
     *
     * @return Array of attack
     */
    String[] getActions() {
        return new String[]{"A"};
    }

    /**
     * Add the opponent for this character.
     *
     * @param character the opponent for this character
     */
    void setOpponent(Character character) {
        opponent = character;
    }

    /**
     * Perform this character's regular attack on its enemy.
     */
    abstract void regularMove();

    /**
     * Perform this character's special move on its opponent.
     */
    abstract void specialMove();

    /**
     * Deal the damage on this character's opponent.
     *
     * @param damage The amount of damage on the opponent.
     */
    abstract void makeMove(int damage);

}
