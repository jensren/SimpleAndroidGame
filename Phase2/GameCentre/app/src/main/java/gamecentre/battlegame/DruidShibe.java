package gamecentre.battlegame;


import java.io.Serializable;

/**
 * DruidShibe character class. Able to perform Regular Move costing 5 Magic points to dealing 8
 * health points damage to its opponent, adds  and a Special Move costing 10 Magic points to
 * dealing 13 Health points damage to its opponent.
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

    /**
     * Perform the special move of the Druid Shibe. Reduce this character's MP, reduce the
     * opponent's HP and heal this Shibe's HP by the amount of damage the opponent took.
     * Add this character into the battle queue twice so it can attack twice next round.
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
