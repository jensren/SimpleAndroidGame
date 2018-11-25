package gamecentre.battlegame;

/**
 * The Ninja Cat character class. Represents the Ninja Cat character.
 */
class NinjaCat extends Character {

    private int specialMoveCost = 9;
    private int specialMoveDamage = 15;
    private int regularMoveDamage = 5;

    @Override
    boolean hasAttackMp() {
        return getMp() >= specialMoveCost;
    }

    @Override
    void regularMove() {
        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);

    }

    /**
     * Reduce this character's magic points and the opponents health points. Then add in this
     * charcter into the battle queue twice.
     */
    @Override
    void specialMove() {
        reduceMp(specialMoveCost);
        reduceHp(specialMoveDamage);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return null;
    }

    @Override
    Character copyCharacter() {
        Character ch = new NinjaCat();
        ch.setHp(this.getHp());
        ch.setMp(this.getMp());
        return ch;
    }
}
