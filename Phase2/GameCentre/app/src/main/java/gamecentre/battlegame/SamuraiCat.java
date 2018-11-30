package gamecentre.battlegame;

import java.io.Serializable;

/**
 * Class representing the Samurai Cat character, a fighter character.
 */
public class SamuraiCat extends Character implements Serializable {

    private static final int REGULAR_MOVE_DAMAGE = 6;
    private static final int SPECIAL_MOVE_DAMAGE = 15;
    private static final int SPECIAL_MOVE_COST = 12;


    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

    /**
     * Plays Samurai Cat's special move. Resets the battle queue that there is only one copy of each
     * character and then its adds itself.
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
