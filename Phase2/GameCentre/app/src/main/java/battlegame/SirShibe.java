package battlegame;

public class SirShibe extends Character {

    private int regularMoveDamage = 10;
    private int specialMoveDamage = 13;
    private int regularCost = 6;
    private int specialCost = 12;
    public AttackManager regularAttack = new AttackManager(regularCost, regularMoveDamage);
    public AttackManager specialAttack = new SirSpecial(specialCost, specialMoveDamage);

    @Override
    boolean hasAttackMp() {
        return getMp() >= regularMoveDamage;
    }

    @Override
    void regularMove() {

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
