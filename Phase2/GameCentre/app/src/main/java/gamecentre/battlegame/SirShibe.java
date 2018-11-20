package gamecentre.battlegame;

/**
 * Class representing the Sir Shibe character.
 */
public class SirShibe extends Character {

    private int regularMoveDamage = 10;
    private int specialMoveDamage = 13;
    private int regularCost = 6;
    private int specialMoveCost = 12;
    public AttackManager regularAttack = new AttackManager(regularCost, regularMoveDamage);
    public AttackManager specialAttack = new SirSpecial(specialMoveCost, specialMoveDamage);

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
