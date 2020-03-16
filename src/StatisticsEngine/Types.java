//Copyright 2020 J Marks
//
//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.

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
            case "failed wounds":
            case "failed hits":
                return e_ReRoll.FAILED_HITS;
            case "wounds":
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
