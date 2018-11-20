package battlegame;

public class SamuraiCat extends Character {

    private int regularMoveDamage = 6;
    private int specialMoveDamage = 15;
    private int specialMoveCost = 12;
    AttackManager regularAttack = new AttackManager(0, regularMoveDamage);
    SamuraiSpecial specialAttack = new SamuraiSpecial(specialMoveCost, specialMoveDamage);

    @Override
    boolean hasAttackMp() {
        return getHp() >= specialMoveCost;
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
