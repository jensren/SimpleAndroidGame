package gamecentre.battlegame;

/**
 * The Ninja Cat character class. Represents the Ninja Cat character.
 */
class NinjaCat extends Character {

    private int specialMoveCost = 9;
    private int specialMoveDamage = 15;
    private int regularMoveDamage = 5;
//    AttackManager regularAttack = new AttackManager(0, regularMoveDamage);
//    NinjaSpecial specialAttack = new NinjaSpecial(specialMoveCost, specialMoveDamage);

    @Override
    boolean hasAttackMp() {
        return getMp() >= specialMoveCost;
    }

    @Override
    void regularMove() {
        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);

    }

    @Override
    void specialMove() {
        reduceMp(specialMoveCost);
        reduceHp(specialMoveDamage);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return null;
    }
}