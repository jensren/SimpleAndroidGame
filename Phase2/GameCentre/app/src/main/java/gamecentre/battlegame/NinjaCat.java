package gamecentre.battlegame;

import java.io.Serializable;

/**
 * The Ninja Cat character class. Represents the Ninja Cat character.
 */
class NinjaCat extends Character implements Serializable {

    private static final int SPECIAL_MOVE_COST = 9;
    private static final int SPECIAL_MOVE_DAMAGE = 15;
    private static final int REGULAR_MOVE_DAMAGE = 5;

    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

    /**
     * Reduce this character's magic points and the opponents health points. Then add in the
     * opponent and then this charcter into the battle queue twice.
     */
    @Override
    void specialMove() {
        getBattleQueue().makeMove();
        getBattleQueue().updatePlayerAttributesStack(this);
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        getBattleQueue().removeCharacter();
        reduceMp(SPECIAL_MOVE_COST);
        getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        getBattleQueue().add(this.getOpponent());
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return "ninja_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
