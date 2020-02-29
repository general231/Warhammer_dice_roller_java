package StatisticsEngine;

public class Types {

    private static DiceRoller diceRoller;
    public enum e_ReRoll {
        NONE,
        ONES,
        FAILED_HITS,
        HITS
    }

    public enum e_HitResult {
        FAIL,
        HIT,
        WOUND,
        MORTAL
    }

    public enum e_DamageType {
        FAIL,
        NORMAL,
        MORTAL
    }

    static e_ReRoll stringToEReRoll(String value)
    {
        switch (value) {
            case "ones":
                return e_ReRoll.ONES;
            case "failed hits":
                return e_ReRoll.FAILED_HITS;
            case "hits":
                return e_ReRoll.HITS;
            default:
                return e_ReRoll.NONE;
    }
    }

    static public int diceToNum(String value)
    {
        if (diceRoller == null) { diceRoller = new DiceRoller(); }
        int output = 0;
        if (value.contains("D")) {
            if (value.length() == 3) {
                int numRolls = Character.getNumericValue(value.charAt(0));
                int diceSize = Character.getNumericValue(value.charAt(2));
                for (int i = 0; i < numRolls; i++) {
                    if (diceSize == 3) {
                        output += diceRoller.rollDiceD3();
                    } else {
                        output += diceRoller.rollDiceD6();
                    }
                }
            } else {
                int diceSize = Character.getNumericValue(value.charAt(1));
                if (diceSize == 3) {
                    output += diceRoller.rollDiceD3();
                } else {
                    output += diceRoller.rollDiceD6();
                }
            }
        } else {
            output = Integer.parseInt(value);
        }
        return output;
    }
}