package gamecentre.battlegame;

import java.io.Serializable;

/**
 * Class representing the Sir Shibe character, a fighter character.
 */
public class SirShibe extends Character implements Serializable {

    /**
     * The damage done by regular move
     */
    private static final int REGULAR_MOVE_DAMAGE = 10;
    /**
     * The damage done by special move
     */
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    /**
     * The amount of MP used by special move
     */
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
     * Plays SirShibe's special move. Resets the battle queue so that there is only one copy of
     * each character in the battle queue.
     */
    @Override
    void specialMove() {
        super.specialMoveHelper(SPECIAL_MOVE_COST, SPECIAL_MOVE_DAMAGE);
        super.fighterCharacterSpecial();
    }

    @Override
    String getSprite() {
        return "sir_shibe";
    }

    @Override
    public String getType() {
        return "dog";
    }

}
