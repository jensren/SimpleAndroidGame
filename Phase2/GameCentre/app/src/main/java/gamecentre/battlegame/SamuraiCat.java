package gamecentre.battlegame;

public class SamuraiCat extends Character {

    private int regularMoveDamage = 6;
    private int specialMoveDamage = 15;
    private int specialMoveCost = 12;
    @Override
    boolean hasAttackMp() {
        return getHp() >= specialMoveCost;
    }

    @Override
    void regularMove() {

        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);
    }

    /**
     * Perform the Samurai Cat's special attack, reduce this character's MP and reduce the HP of
     * opponent. Reset the battle queue so that each character appears once.
     */
    @Override
    void specialMove() {
        Character ch1 = getBattleQueue().getNextCharacter();
        BattleQueue bq = ch1.getBattleQueue();
        Character ch;

        reduceMp(specialMoveCost);
        this.getOpponent().reduceHp(specialMoveDamage);

        int p1Count = 0;
        int p2Count = 0;

        while (!bq.isEmpty()) {
            ch = bq.removeCharacter();
            if (ch == ch1) { p1Count += 1;}
            else {p2Count += 1;}
        }
        if (p1Count > 0) {
            bq.add(ch1);
        }
        if (p2Count > 0) {
            bq.add(ch1.getOpponent());
        }

    }

    @Override
    String getSprite() {
        return null;
    }

    @Override
    public String getType() {
        return "cat";
    }

}
