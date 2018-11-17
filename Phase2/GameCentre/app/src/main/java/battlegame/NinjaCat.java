package battlegame;

class NinjaCat extends Character {

    private int regularMoveDamage = 9;
    private int specialMoveDamage = 15;

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
