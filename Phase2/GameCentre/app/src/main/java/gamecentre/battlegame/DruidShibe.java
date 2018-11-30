package gamecentre.battlegame;


import java.io.Serializable;

/**
 * A class representing the Druid Shibe character, a healer character.
 */
class DruidShibe extends Character implements Serializable {

    /**
     * The damage done by regular move
     */
    private static final int REGULAR_MOVE_DAMAGE = 8;
    /**
     * The damage done by special move
     */
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    /**
     * The amount of MP used by special move
     */
    private static final int SPECIAL_MOVE_COST = 10;

    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

    /**
     * Plays Druid Shibe's special move.
     * Increases this characters Health Points by the amount of damage the opponent took.
     */
    @Override
    void specialMove() {
        super.specialMoveHelper(SPECIAL_MOVE_COST, SPECIAL_MOVE_DAMAGE);
        super.healerCharacterSpecial(SPECIAL_MOVE_DAMAGE);
    }

    @Override
    String getSprite() {
        return "druid_shibe";
    }

    @Override
    public String getType() {
        return "dog";
    }

}
