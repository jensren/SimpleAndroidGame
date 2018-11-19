package battlegame;

abstract class Character {
    // character's special attack deflects opponent's move back at them

    private int hp = 100;
    private int mp = 100;
    private Character opponent;
    private BattleQueue battleQueue;


    abstract boolean hasAttackMp();

    int getMp() {
        return mp;
    }

    int getHp() { return hp;
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

    /**
     * Get the next sprite for the character to display.
     * @return The name of the next sprite.
     */
    abstract String getSprite();

    /**
     * Reduce this character's MP by damage if they have enough MP, else 0.
     * @param amount
     */
    void reduceMp(int amount) {
        if (mp >= amount) { mp = mp - amount; }
        else { mp = 0; }
    }

    /**
     * Reduce the Hp of this character by damage.
     * @param damage Amount to reduce HP
     */
    void reduceHp(int damage) {
        Math.abs(hp - damage);
    }

    /**
     * Return this character's BattleQueue.
     * @return Character's Battle Queue
     */
    public BattleQueue getBattleQueue() {
        return battleQueue;
    }

    /**
     * Set this character's Battle Queue.
     * @param battleQueue The Battle Queue this character and its Opponent will use.
     */
    public void setBattleQueue(BattleQueue battleQueue) {
        this.battleQueue = battleQueue;
    }
}
