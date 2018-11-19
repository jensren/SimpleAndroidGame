package battlegame;

/**
 * Represent the DruidShibe's special attack.
 */
class DruidSpecial extends AttackManager {

    private int cost;
    private int damage;

    DruidSpecial(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * Add the Druid Shibe into the Battle Queue twice so it attacks twice consecutively next round.
     * @param character The character performing the attack
     * @param opponent  Character taking damage.
     */
    @Override
    void performAttack(Character character, Character opponent) {
        character.reduceMp(cost);
        opponent.reduceHp(damage);
        character.getBattleQueue().add(character);
        character.getBattleQueue().add(character);

    }
}
