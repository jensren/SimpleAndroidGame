package gamecentre.battlegame;

import java.io.Serializable;

public class SamuraiCat extends Character implements Serializable {

    private static final int REGULAR_MOVE_DAMAGE = 6;
    private static final int SPECIAL_MOVE_DAMAGE = 15;
    private static final int SPECIAL_MOVE_COST = 12;
    //BattleQueue =


    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

    /**
     * Perform the Samurai Cat's special attack, reduce this character's MP and reduce the HP of
     * opponent. Reset the battle queue so that each character appears once.
     */
    @Override
    void specialMove() {
        super.specialMoveHelper(SPECIAL_MOVE_COST, SPECIAL_MOVE_DAMAGE);
        super.fighterCharacterSpecial();
    }

    @Override
    String getSprite() {
        return "samurai_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
