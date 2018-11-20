package gamecentre.battlegame;


/**
 * Representation of the Shaman Cat character.
 */
public class ShamanCat extends Character {

    private int regularMoveDamage = 7;
    private int specialMoveDamage = 13;
    private int specialMoveCost = 22;
    AttackManager regularAttack = new AttackManager(0, regularMoveDamage);
    ShamanSpecial specialAttack = new ShamanSpecial(specialMoveCost, specialMoveDamage);

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
