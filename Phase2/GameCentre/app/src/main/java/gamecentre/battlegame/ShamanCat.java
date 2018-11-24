package gamecentre.battlegame;


/**
 * Representation of the Shaman Cat character.
 */
public class ShamanCat extends Character {

    private int regularMoveDamage = 7;
    private int specialMoveDamage = 13;
    private int specialMoveCost = 22;

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
     * Perform the Shaman Cat's special move by reducing this character's MP, reduce the opponent's
     * HP and heal this character by the amount of damage it's opponent takes.
     */
    @Override
    void specialMove() {
        reduceMp(specialMoveCost);
        getOpponent().reduceHp(specialMoveDamage);
        increaseHp(specialMoveDamage);
        getBattleQueue().add(this);

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
