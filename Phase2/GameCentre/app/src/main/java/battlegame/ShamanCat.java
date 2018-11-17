package battlegame;

public class ShamanCat extends Character {

    private int regularMoveDamage = 7;
    private int specialMoveDamage = 13;

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
