package battlegame;

public class SamuraiCat extends Character {

    private int regularMoveDamage = 6;
    private int specialMoveDamage = 15;

    @Override
    boolean hasAttackMp() {
        return getHp() >= regularMoveDamage;
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
