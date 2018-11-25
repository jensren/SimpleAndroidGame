package gamecentre.battlegame;

/**
 * Class representing the Sir Shibe character.
 */
public class SirShibe extends Character {

    private static final int REGULAR_MOVE_DAMAGE = 10;
    private static final int SPECIAL_MOVE_DAMAGE = 13;
    private static final int SPECIAL_MOVE_COST = 12;

    @Override
    boolean hasAttackMp() {
        return getMp() >= SPECIAL_MOVE_COST;
    }

    @Override
    void regularMove() {
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        this.getOpponent().reduceHp(REGULAR_MOVE_DAMAGE);
        getBattleQueue().add(this);
    }

    /**
     * Perform the special move of Sir Shibe with reduces this character's MP, reduces the
     * opponent's HP and resets the battle queue so that there is only one copy of each character.
     */
    @Override
    void specialMove() {
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        Character ch1 = getBattleQueue().getNextCharacter();
        BattleQueue bq = ch1.getBattleQueue();
        Character ch;

        reduceMp(SPECIAL_MOVE_COST);
        this.getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);

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
        return "sir_shibe";
    }

    @Override
    public String getType() {
        return "dog";
    }

}
