package gamecentre.battlegame;

/**
 * The Ninja Cat character class. Represents the Ninja Cat character.
 */
class NinjaCat extends Character {

    private int specialMoveCost = 9;
    private int specialMoveDamage = 15;
    private int regularMoveDamage = 5;
    AttackManager regularAttack = new AttackManager(0, regularMoveDamage);
    NinjaSpecial specialAttack = new NinjaSpecial(specialMoveCost, specialMoveDamage);

    @Override
    boolean hasAttackMp() {
        return getMp() >= specialMoveCost;
    }

    @Override
    void regularMove() {
        regularAttack.performAttack(this, this.getOpponent());
    }

    @Override
    void specialMove() {
        specialAttack.performAttack(this, this.getOpponent());

    }

    @Override
    String getSprite() {
        return null;
    }
}
