package com.company;

import java.util.ArrayList;
import java.util.Random;

enum e_ReRoll {
    NONE,
    ONES,
    FAILED_HITS,
    HITS
}

enum e_HitResult {
    FAIL,
    HIT,
    WOUND,
    MORTAL
}

enum e_DamageType {
    FAIL,
    NORMAL,
    MORTAL
}

class DiceRoller
{
    Random myRandom;
    int myMaxValue;

    public DiceRoller(int maxValue)
    {
        myRandom = new Random();
        myMaxValue = maxValue;
    }

    public int rollDice()
    {
        return (int)(1 + myMaxValue * Math.random());
    }
}

class SuccessObject
{
    private int mySuccessValue;
    public int myDiceModifier;
    public e_ReRoll myReRoll;
    public DiceRoller myDiceRoller;

    public SuccessObject(int value)
    {
        mySuccessValue = value;
        myReRoll = e_ReRoll.NONE;
        myDiceModifier = 0;
        myDiceRoller = new DiceRoller(6);
    }

    public boolean doesDiceExplode(int diceValue, int diceSuccessValue, boolean isModified)
    {
        if (isModified)
        {
            diceValue = diceValue - myDiceModifier;
        }
        return diceValue > diceSuccessValue;
    }

    public boolean canIReRoll(int diceValue)
    {
        switch (myReRoll) {
            case NONE:
                return false;
            case ONES:
                if (diceValue == 1)
                {
                    return true;
                }
            case FAILED_HITS:
                if (diceValue < mySuccessValue)
                {
                    return true;
                }
            case HITS:
                diceValue += myDiceModifier;
                if (diceValue < mySuccessValue)
                {
                    return true;
                }
            default:
                return false;
        }
    }

    public boolean doISucceed(int diceValue)
    {
        int modifiedDiceValue = diceValue + myDiceModifier;
        return modifiedDiceValue >= mySuccessValue && diceValue != 1;
    }
}

class Hitter extends SuccessObject
{
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

    public void setAutoWound(int diceRequirement, boolean isModified)
    {
        myTestForAutoWound = true;
        myAutoWoundRequirment = diceRequirement;
        myAutoWoundIsModified = isModified;
    }

    public void setMortalWound(int diceRequirement, boolean isModified)
    {
        myTestForMortalWound = true;
        myMortalWoundRequirment = diceRequirement;
        myMortalWoundIsModified = isModified;
    }

    public void setExplodingHits(int diceRequirement, boolean isModified, int bonusHits)
    {
        myTestForExplodingHit = true;
        myExplidingHitsRequirment = diceRequirement;
        myExplodingHitsIsModified = isModified;
        myExplodingHitsBonus = bonusHits;
    }

    public void setAutoSuccess(boolean value)
    {
        myIsAutoSuccess = value;
    }

    public ArrayList<e_HitResult> rollToHit(int diceValue)
    {
        return rollToHit(diceValue, true);
    }

    public ArrayList<e_HitResult> rollToHit(int diceValue, boolean applyReRoll)
    {
        ArrayList<e_HitResult> output = new ArrayList<>();

        if (myIsAutoSuccess)
        {
            output.add(e_HitResult.HIT);
            return output;
        }
        if (myTestForAutoWound)
        {
            if (doesDiceExplode(diceValue, myAutoWoundRequirment, myAutoWoundIsModified))
            {
                output.add(e_HitResult.WOUND);
                return output;
            }
        }
        if (myTestForMortalWound)
        {
            if (doesDiceExplode(diceValue, myMortalWoundRequirment, myMortalWoundIsModified))
            {
                output.add(e_HitResult.MORTAL);
            }
        }
        if (myTestForExplodingHit)
        {
            if (doesDiceExplode(diceValue, myExplidingHitsRequirment, myExplodingHitsIsModified))
            {
                for (int i = 0; i < myExplodingHitsBonus; i++)
                {
                    output.add(e_HitResult.HIT);
                }
            }
        }
        if (doISucceed(diceValue))
        {
            output.add(e_HitResult.HIT);
        }
        if (output.isEmpty() && applyReRoll)
        {
            if (canIReRoll(diceValue))
            {
                int newDiceValue = super.myDiceRoller.rollDice();
                output.addAll(this.rollToHit(newDiceValue, false));
            }
        }

        return output;
    }
}

public class Main {

    public static void main(String[] args) {
	SuccessObject test = new SuccessObject(8);
	DiceRoller dice = new DiceRoller(6);
    Hitter aHitter = new Hitter(3);
    if (aHitter.rollToHit(3).contains(e_HitResult.HIT))
    {
        System.out.println("Pass");
    }
    else
    {
        System.out.println("Fail");
    }
    }
}
