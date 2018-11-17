package battlegame;

public class SirShibe extends Character {

    private int regularMoveDamage = 10;
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
