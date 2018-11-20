package battlegame;

class ShamanSpecial extends AttackManager {

    private int cost;
    private int damage;

    /**
     * instantiate this Shaman Special Attack with cost and damage.
     * @param cost Amount of MP this attack uses.
     * @param damage Amount of damage it deals on the opponent
     */
    ShamanSpecial(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * The character heals themselves (gain HP) by the amount of damage they do to their opponent.
     *
     * @param character The character performing the attack
     * @param opponent  Character taking damage.
     */
    @Override
    void performAttack(Character character, Character opponent) {

        character.reduceMp(cost);
        opponent.reduceHp(damage);
        character.increaseHp(damage);
        character.getBattleQueue().add(character);

    }
}
