package gamecentre.battlegame;


/**
 * DruidShibe character class. Able to perform Regular Move costing 5 Magic points to dealing 8
 * health points damage to its opponent, adds  and a Special Move costing 10 Magic points to
 * dealing 13 Health points damage to its opponent.
 */
class DruidShibe extends Character {

    private int regularMoveDamage = 8;
    private int specialMoveDamage = 13;
    private int specialMoveCost = 10;


    @Override
    boolean hasAttackMp() {
        return getHp() >= specialMoveCost;
    }

    @Override
    void regularMove() {
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);
    }

    /**
     * Perform the special move of the Druid Shibe. Reduce this character's MP adn reduce the
     * opponent's HP. Add this character into the battle queue twice so it can attack twice next
     * round.
     */
    @Override
    void specialMove() {
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        reduceMp(specialMoveCost);
        this.getOpponent().reduceHp(specialMoveDamage);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return null;
    }

}
