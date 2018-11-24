package gamecentre.battlegame;

/**
 * Class representing the Sir Shibe character.
 */
public class SirShibe extends Character {

    private int regularMoveDamage = 10;
    private int specialMoveDamage = 13;
    private int specialMoveCost = 12;

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
     * Perform the special move of Sir Shibe with reduces this character's MP, reduces the
     * opponent's HP and resets the battle queue so that there is only one copy of each character.
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
        return "dog";
    }

}
