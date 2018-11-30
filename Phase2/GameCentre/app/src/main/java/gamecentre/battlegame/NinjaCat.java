package gamecentre.battlegame;

import java.io.Serializable;

/**
 * A class representing the Ninja Cat character, a stealth character.
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
     * Plays Ninja cat's special move.
     * Adds its opponent to the battle queue and then itself twice so that this character can
     * attack twice in concession after its opponent attacks.
     */
    @Override
    void specialMove() {
        super.specialMoveHelper(SPECIAL_MOVE_COST, SPECIAL_MOVE_DAMAGE);
        super.stealthCharacterSpecial();
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
