package gamecentre.battlegame;

class SamuraiSpecial extends AttackManager {

    private int cost;
    private int damage;

    SamuraiSpecial(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    @Override
    void performAttack(Character character, Character opponent) {
        Character ch1 = character.getBattleQueue().getNextCharacter();
        BattleQueue bq = ch1.getBattleQueue();
        Character ch;

        character.reduceMp(cost);
        opponent.reduceHp(damage);
        character.getBattleQueue().add(character);

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
}
