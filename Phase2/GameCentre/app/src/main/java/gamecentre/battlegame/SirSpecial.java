//package gamecentre.battlegame;
//
///**
// * Represent Sir Shibe's special attack.
// */
//class SirSpecial extends AttackManager {
//
//    private int cost;
//    private int damage;
//
//    SirSpecial(int cost, int damage) {
//        this.cost = cost;
//        this.damage = damage;
//    }
//
//    /**
//     * Reset this Battle Queue so that there is only one copy of each character in it and add in
//     * this character twice.
//     *
//     * @param character The character performing the attack
//     * @param opponent  Character taking damage.
//     */
//    @Override
//    void performAttack(Character character, Character opponent) {
//        Character ch1 = character.getBattleQueue().getNextCharacter();
//        BattleQueue bq = ch1.getBattleQueue();
//        Character ch;
//
//        character.reduceMp(cost);
//        opponent.reduceHp(damage);
//        character.getBattleQueue().add(character);
//
//        int p1Count = 0;
//        int p2Count = 0;
//
//        while (!bq.isEmpty()) {
//            ch = bq.removeCharacter();
//            if (ch == ch1) { p1Count += 1;}
//            else {p2Count += 1;}
//        }
//        if (p1Count > 0) {
//            bq.add(ch1);
//        }
//        if (p2Count > 0) {
//            bq.add(ch1.getOpponent());
//        }
//
//
//    }
//}
