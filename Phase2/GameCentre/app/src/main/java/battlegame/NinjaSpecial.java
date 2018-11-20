package battlegame;

class NinjaSpecial extends AttackManager {

    private int cost;
    private int damage;

    NinjaSpecial(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    /**
     * Reduce characters MP and deal damage to its opponent. Add the character into the battle
     * queue twice so it can attack twice next round.
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
