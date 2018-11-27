package gamecentre.battlegame;

import java.io.Serializable;

public class SamuraiCat extends Character implements Serializable {

    private static final int REGULAR_MOVE_DAMAGE = 6;
    private static final int SPECIAL_MOVE_DAMAGE = 15;
    private static final int SPECIAL_MOVE_COST = 12;
    //BattleQueue =


    @Override
    boolean hasAttackMp() {
        return getHp() >= SPECIAL_MOVE_COST;
    }

    @Override
    void regularMove() {
        getBattleQueue().makeMove();
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        getBattleQueue().removeCharacter();
        getOpponent().reduceHp(REGULAR_MOVE_DAMAGE);
        getBattleQueue().add(this);
    }

    /**
     * Perform the Samurai Cat's special attack, reduce this character's MP and reduce the HP of
     * opponent. Reset the battle queue so that each character appears once.
     */
    @Override
    void specialMove() {

        getBattleQueue().makeMove();
        getBattleQueue().updateUndoStack(getBattleQueue().copyBq());
        BattleQueue bq = getBattleQueue();

        reduceMp(SPECIAL_MOVE_COST);
        getOpponent().reduceHp(SPECIAL_MOVE_DAMAGE);
        while (!bq.isEmpty()) {
            bq.removeCharacter();
        }
        bq.add(bq.getPlayer1());
        bq.add(bq.getPlayer2());
        bq.add(this);


    }

    @Override
    String getSprite() {
        return "samurai_cat";
    }

    @Override
    public String getType() {
        return "cat";
    }

}
