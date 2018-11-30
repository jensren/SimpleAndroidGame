package gamecentre.battlegame;

import java.io.Serializable;

/**
 * Class representing the Sir Shibe character, a fighter character.
 * A fighter Character's special attack resets the battle queue so that there is only one copy of
 * each character in the battle queue.
 */
public class SirShibe extends Character implements Serializable {

    private static final int REGULAR_MOVE_DAMAGE = 10;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 12;

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
