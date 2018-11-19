package battlegame;

/**
 * Represent the Detective Shibe's special attack.
 */
class DetectiveSpecial extends AttackManager {

    private int cost;
    private int damage;

    DetectiveSpecial(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * Add the opponent into the Battle Queue before adding in this character 2 times.
     * @param character The character performing the attack
     * @param opponent  Character taking damage.
     */
    @Override
    void performAttack(Character character, Character opponent) {
        character.reduceMp(cost);
        opponent.reduceHp(damage);
        character.getBattleQueue().add(opponent);
        character.getBattleQueue().add(character);
        character.getBattleQueue().add(character);

    }
}
