package gamecentre.battlegame;


import java.io.Serializable;

/**
 * A class representing the Druid Shibe character, a healer character. Druid Shibe's special move
 * increases this characters Health Points byt he amount of damage the opponent took.
 */
class DruidShibe extends Character implements Serializable {

    private static final int REGULAR_MOVE_DAMAGE = 8;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 10;


    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

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
