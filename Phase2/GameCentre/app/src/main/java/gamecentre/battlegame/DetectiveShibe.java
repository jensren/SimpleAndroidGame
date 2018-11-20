package gamecentre.battlegame;

/**
 * Representation of the Detective Shibe character.
 */
public class DetectiveShibe extends Character {

    private int regularMoveDamage = 7;
    private int specialMoveDamage = 12;
    private int specialMoveCost = 15;
//    AttackManager regularAttack = new AttackManager(0, regularMoveDamage);
//    DetectiveSpecial specialAttack = new DetectiveSpecial(specialMoveCost, specialMoveDamage);

    @Override
    boolean hasAttackMp() {
        return getMp() >= specialMoveCost;
    }

    @Override
    void regularMove() {
        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);
        //regularAttack.performAttack(this, this.getOpponent());
    }

    @Override
    void specialMove() {
        reduceMp(specialMoveCost);
        getOpponent().reduceHp(specialMoveDamage);
        getBattleQueue().add(getOpponent());
        getBattleQueue().add(this);
        getBattleQueue().add(this);

    }

    @Override
    String getSprite() {
        return null;
    }
}
