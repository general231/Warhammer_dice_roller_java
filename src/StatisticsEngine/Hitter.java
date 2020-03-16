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

import java.util.ArrayList;

public class Hitter extends SuccessObject {
    private boolean myTestForAutoWound;
    private int myAutoWoundRequirment;
    private boolean myAutoWoundIsModified;

    private boolean myTestForMortalWound;
    private int myMortalWoundRequirment;
    private boolean myMortalWoundIsModified;

    private boolean myTestForExplodingHit;
    private int myExplidingHitsRequirment;
    private int myExplodingHitsBonus;
    private boolean myExplodingHitsIsModified;

    private boolean myIsAutoSuccess;

    public Hitter(int value) {
        super(value);
        myTestForAutoWound = false;
        myTestForMortalWound = false;
        myTestForExplodingHit = false;
        myIsAutoSuccess = false;
    }

    public void setAutoWound(int diceRequirement, boolean isModified) {
        myTestForAutoWound = true;
        myAutoWoundRequirment = diceRequirement;
        myAutoWoundIsModified = isModified;
    }

    public void setMortalWound(int diceRequirement, boolean isModified) {
        myTestForMortalWound = true;
        myMortalWoundRequirment = diceRequirement;
        myMortalWoundIsModified = isModified;
    }

    public void setExplodingHits(int diceRequirement, boolean isModified, int bonusHits) {
        myTestForExplodingHit = true;
        myExplidingHitsRequirment = diceRequirement;
        myExplodingHitsIsModified = isModified;
        myExplodingHitsBonus = bonusHits;
    }

    public void setAutoSuccess(boolean value)
    {
        myIsAutoSuccess = value;
    }

    public ArrayList<Types.e_HitResult> rollToHit(int diceValue)
    {
        return rollToHit(diceValue, true);
    }

    public ArrayList<Types.e_HitResult> rollToHit(int diceValue, boolean applyReRoll) {
        ArrayList<Types.e_HitResult> output = new ArrayList<>();

        if (myIsAutoSuccess) {
            output.add(Types.e_HitResult.HIT);
            return output;
        }
        if (myTestForAutoWound) {
            if (doesDiceExplode(diceValue, myAutoWoundRequirment, myAutoWoundIsModified))
            {
                output.add(Types.e_HitResult.WOUND);
                return output;
            }
        }
        if (myTestForMortalWound) {
            if (doesDiceExplode(diceValue, myMortalWoundRequirment, myMortalWoundIsModified))
            {
                output.add(Types.e_HitResult.MORTAL);
            }
        }
        if (myTestForExplodingHit) {
            if (doesDiceExplode(diceValue, myExplidingHitsRequirment, myExplodingHitsIsModified))
            {
                for (int i = 0; i < myExplodingHitsBonus; i++)
                {
                    output.add(Types.e_HitResult.HIT);
                }
            }
        }
        if (doISucceed(diceValue)) {
            output.add(Types.e_HitResult.HIT);
        }
        if (output.isEmpty() && applyReRoll) {
            if (canIReRoll(diceValue)) {
                int newDiceValue = super.myDiceRoller.rollDiceD6();
                output.addAll(this.rollToHit(newDiceValue, false));
            }
        }

        return output;
    }
}
