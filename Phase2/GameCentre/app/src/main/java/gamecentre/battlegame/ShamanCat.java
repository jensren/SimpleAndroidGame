package gamecentre.battlegame;


import java.io.Serializable;

/**
 * Representation of the Shaman Cat character, a healer character.
 */
public class ShamanCat extends Character implements Serializable {

    /**
     * The damage done by regular move
     */
    private static final int REGULAR_MOVE_DAMAGE = 7;
    /**
     * The damage done by special move
     */
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    /**
     * The amount of MP used by special move
     */
    private static final int SPECIAL_MOVE_COST = 22;

    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

    /**
     * Plays Shaman Cat's special move. Increases this character's Health Points by the amount of
     * damage its opponent takes.
     */
    @Override
    void specialMove() {
        super.specialMoveHelper(SPECIAL_MOVE_COST, SPECIAL_MOVE_DAMAGE);
        super.healerCharacterSpecial(SPECIAL_MOVE_DAMAGE);
    }


    @Override
    String getSprite() {
        return "shaman_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
