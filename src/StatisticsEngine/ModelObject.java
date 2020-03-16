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

public class ModelObject {
    private int myWoundCharacteristic;
    private boolean myIsFnp;
    private int myFnp;
    private DiceRoller myDiceRoller;
    private boolean myHalveDamage;
    private boolean myIsReduceDamage;
    private int myReduceDamageAmount;

    private int myTotalDamageReceived;
    private int myTotalLostModels;
    private int myWoundsRemaining;

    public void setHalveDamage(boolean value)
    {
        myHalveDamage = value;
    }
    public void setMyReduceDamageAmount(int value)
    {
        myReduceDamageAmount = value;
        myIsReduceDamage = true;
    }
    public void setMyFnp(int value)
    {
        if (value != 0) {
            myFnp = value;
            myIsFnp = true;
        }
    }
    public int getMyTotalDamageReceived() { return myTotalDamageReceived; }
    public int getMyTotalLostModels() { return myTotalLostModels; }
    public void reset()
    {
        myTotalDamageReceived = 0;
        myTotalLostModels = 0;
        myWoundsRemaining = myWoundCharacteristic;
    }

    public void applyDamage(DamageObject someDamage)
    {
        if (myHalveDamage)
        {
            someDamage.halveDamage();
        }
        if (myIsReduceDamage)
        {
            someDamage.reduceDamage(myReduceDamageAmount);
        }
        int damageToApply = someDamage.getDamage();
        for (int i = 0; i < someDamage.getDamage(); i++)
        {
            int fnpDiceValue = myDiceRoller.rollDiceD6();
            if (myFnp <= fnpDiceValue)
            {
                damageToApply--;
            }
        }
        myTotalDamageReceived += damageToApply;
        myWoundsRemaining -= damageToApply;
        if (myWoundsRemaining < 1)
        {
            myTotalLostModels += 1;
            myWoundsRemaining = myWoundCharacteristic;
        }
    }

    public ModelObject(int woundCharacteristic)
    {
        myWoundCharacteristic = woundCharacteristic;
        myIsReduceDamage = false;
        myIsFnp = false;
        myHalveDamage = false;
        myDiceRoller = new DiceRoller();
    }
}
