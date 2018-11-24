package gamecentre.battlegame;

/**
 * Representation of the Detective Shibe character.
 */
public class DetectiveShibe extends Character {

    private int regularMoveDamage = 7;
    private int specialMoveDamage = 12;
    private int specialMoveCost = 15;

    @Override
    boolean hasAttackMp() {
        return getMp() >= specialMoveCost;
    }

    @Override
    void regularMove() {
        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);

    }

    @Override
    void specialMove() {
        reduceMp(specialMoveCost);
        getOpponent().reduceHp(specialMoveDamage);
        getBattleQueue().add(this);
        getBattleQueue().add(this);

    }

    @Override
    String getSprite() {
        return "detective_shibe";
    }

    public String getType() {
        return "dog";
    }
}
