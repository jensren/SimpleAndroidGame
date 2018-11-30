package gamecentre.battlegame;


import java.io.Serializable;

/**
 * Representation of the Shaman Cat character, a healer character. Shaman Cat's special move
 * increases this character's Health Points by the amount of damage its opponent takes.
 */
public class ShamanCat extends Character implements Serializable {

    private static final int REGULAR_MOVE_DAMAGE = 7;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 22;

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
        return "shaman_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
