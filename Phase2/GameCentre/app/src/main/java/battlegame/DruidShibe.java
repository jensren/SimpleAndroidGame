package battlegame;


/**
 * DruidShibe character class. Able to perform Regular Move costing 5 Magic points to dealing 8
 * health points damage to its opponent, adds  and a Special Move costing 10 Magic points to dealing 13
 * Health points damage to its opponent.
 */
class DruidShibe extends Character {

    private int regularMoveDamage = 8;
    private int specialMoveDamage = 13;
    private int regularCost = 5;
    private int specialCost = 10;

    @Override
    boolean hasAttackMp() {
        return getHp() >= regularMoveDamage;
    }

    @Override
    void regularMove() {
        if (hasAttackMp()) {

        }

    }

    @Override
    void specialMove() {

    }

    @Override
    void makeMove(int damage) {

    }

    @Override
    String getSprite() {
        return null;
    }
}
