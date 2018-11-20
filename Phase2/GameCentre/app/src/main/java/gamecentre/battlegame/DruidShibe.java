package gamecentre.battlegame;


/**
 * DruidShibe character class. Able to perform Regular Move costing 5 Magic points to dealing 8
 * health points damage to its opponent, adds  and a Special Move costing 10 Magic points to
 * dealing 13 Health points damage to its opponent.
 */
class DruidShibe extends Character {

    private int regularMoveDamage = 8;
    private int specialMoveDamage = 13;
    private int regularCost = 5;
    private int specialMoveCost = 10;
    //AttackManager regularAttack = new AttackManager(regularCost, regularMoveDamage);
    //DruidSpecial specialAttack = new DruidSpecial(specialMoveCost, specialMoveDamage);


    @Override
    boolean hasAttackMp() {
        return getHp() >= specialMoveCost;
    }

    @Override
    void regularMove() {

        this.getOpponent().reduceHp(regularMoveDamage);
        getBattleQueue().add(this);
        //regularAttack.performAttack(this, this.getOpponent());
    }

    @Override
    void specialMove() {

        //specialAttack.performAttack(this, this.getOpponent());
        reduceMp(specialMoveCost);
        this.getOpponent().reduceHp(specialMoveDamage);
        getBattleQueue().add(this);
        getBattleQueue().add(this);
    }

    @Override
    String getSprite() {
        return null;
    }
}
