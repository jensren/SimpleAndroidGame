package gamecentre.battlegame;

import java.io.Serializable;

/**
 * A class representing the Detective Shibe character, a stealth character.
 */
public class DetectiveShibe extends Character implements Serializable {

    /**
     * The damage done by regular move
     */
    private static final int REGULAR_MOVE_DAMAGE = 7;
    /**
     * The damage done by special move
     */
    private static final int SPECIAL_MOVE_DAMAGE = 12;
    /**
     * The amount of MP used by special move
     */
    private static final int SPECIAL_MOVE_COST = 15;

    @Override
    boolean hasAttackMp() {
        return super.hasAttackMpHelper(SPECIAL_MOVE_COST);
    }

    @Override
    void regularMove() {
        super.regularMoveHelper(REGULAR_MOVE_DAMAGE);
    }

    /**
     * Plays Detective Shibe's special move.
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
        return "detective_shibe";
    }

    public String getType() {
        return "dog";
    }
}
