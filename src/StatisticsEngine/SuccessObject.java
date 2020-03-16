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

public class SuccessObject {
    protected int mySuccessValue;
    private int myDiceModifier;
    private Types.e_ReRoll myReRoll;
    protected DiceRoller myDiceRoller;

    public SuccessObject(int value)
    {
        mySuccessValue = value;
        myReRoll = Types.e_ReRoll.NONE;
        myDiceModifier = 0;
        myDiceRoller = new DiceRoller();
    }

    public boolean doesDiceExplode(int diceValue, int diceSuccessValue, boolean isModified)
    {
        if (isModified)
        {
            diceValue = diceValue + myDiceModifier;
        }
        return diceValue >= diceSuccessValue;
    }

    public boolean canIReRoll(int diceValue)
    {
        switch (myReRoll) {
            case NONE:
                return false;
            case ONES:
                if (diceValue == 1) {
                    return true;
                }
                break;
            case FAILED_HITS:
                if (diceValue < mySuccessValue) {
                    return true;
                }
                break;
            case HITS:
                diceValue += myDiceModifier;
                if (diceValue < mySuccessValue) {
                    return true;
                }
        }
        return false;
    }

    public boolean doISucceed(int diceValue)
    {
        int modifiedDiceValue = diceValue + myDiceModifier;
        return modifiedDiceValue >= mySuccessValue && diceValue != 1;
    }

    public int getDiceModifier()
    {
        return myDiceModifier;
    }
    public void setDiceModifier(int value)
    {
        myDiceModifier = value;
    }

    public int getSuccessValue()
    {
        return mySuccessValue;
    }

    public Types.e_ReRoll getReRoll()
    {
        return myReRoll;
    }
    public void setReRoll(Types.e_ReRoll value)
    {
        myReRoll = value;
    }

}
