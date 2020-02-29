package StatisticsEngine;

import java.util.ArrayList;

public class Wounder extends SuccessObject{
    private int myStrength;
    private int myToughness;

    private String myBaseDamage;
    private int myBaseAp;

    private boolean myTestForRending;
    private int myRendingRequirement;
    private boolean myRendingIsModified;
    private int myRendingBonus;

    private boolean myTestForExplodingDamage;
    private int myExplodingDamageRequirement;
    private boolean myExplodingDamageIsModified;
    private String myExplodingDamageBonus;

    private boolean myTestForExplodingWounds;
    private int myExplodingWoundsRequirement;
    private boolean myExplodingWoundsIsModified;
    private int myExplodingWoundsBonus;

    private boolean myTestForMortalWounds;
    private int myMortalWoundsRequirement;
    private boolean myMortalWoundsIsModified;
    private String myMortalWoundsBonus;

    public Wounder(int strength, int toughness, String baseDamage, int baseAp)
    {
        super(4);
        myTestForRending = false;
        myTestForExplodingDamage = false;
        myTestForExplodingWounds = false;
        myTestForMortalWounds = false;
        myStrength = strength;
        myToughness = toughness;
        myBaseAp = baseAp;
        myBaseDamage = baseDamage;

        if (strength >= toughness*2)
        {
            super.mySuccessValue = 2;
        }
        else if (strength > toughness)
        {
            super.mySuccessValue = 3;
        }
        else if (strength == toughness)
        {
            super.mySuccessValue = 4;
        }
        else if (toughness >= strength*2)
        {
            super.mySuccessValue = 6;
        }
        else
        {
            super.mySuccessValue = 5;
        }
    }

    public void setRending(int diceRequirement, boolean isModified, int bonus)
    {
        myTestForRending  = true;
        myRendingRequirement = diceRequirement;
        myRendingIsModified = isModified;
        myRendingBonus = bonus;
    }

    public void setExplodingDamage(int diceRequirement, boolean isModified, String bonus)
    {
        myTestForExplodingDamage = true;
        myExplodingDamageRequirement = diceRequirement;
        myExplodingDamageIsModified = isModified;
        myExplodingDamageBonus = bonus;
    }

    public void setMortalWounds(int diceRequirement, boolean isModified, String bonus)
    {
        myTestForMortalWounds = true;
        myMortalWoundsRequirement = diceRequirement;
        myMortalWoundsIsModified = isModified;
        myMortalWoundsBonus = bonus;
    }

    public void setExplodingWounds(int diceRequirement, boolean isModified, int bonus)
    {
        myTestForExplodingWounds = true;
        myExplodingWoundsRequirement = diceRequirement;
        myExplodingWoundsIsModified = isModified;
        myExplodingWoundsBonus = bonus;
    }

    private ArrayList<DamageObject> createDamageObject(Types.e_DamageType type, int bonusDamage, int bonusAp)
    {
        ArrayList<DamageObject> output = new ArrayList<>();
        if (type == Types.e_DamageType.MORTAL)
        {
            for (int i = 0; i < bonusDamage; i++)
            {
                output.add(new DamageObject(1, 0, Types.e_DamageType.MORTAL));
            }
            return output;
        }
        int damage = Types.diceToNum(myBaseDamage) + bonusDamage;
        output.add(new DamageObject(damage, myBaseAp + bonusAp, Types.e_DamageType.NORMAL));
        return output;
    }

    public ArrayList<DamageObject> rollToWound(Types.e_HitResult type, int diceValue)
    {
        ArrayList<DamageObject> output = new ArrayList<>();

        if (type == Types.e_HitResult.MORTAL)
        {
            output.addAll(createDamageObject(Types.e_DamageType.MORTAL, 1, 0));
            return output;
        }
        else if (type == Types.e_HitResult.WOUND)
        {
            output.addAll(createDamageObject(Types.e_DamageType.NORMAL, 0, 0));
            return output;
        }
        else if (type == Types.e_HitResult.FAIL)
        {
            return output;
        }
        else
        {
            if (myTestForRending)
            {
                if (super.doesDiceExplode(diceValue, myRendingRequirement, myRendingIsModified))
                {
                    output.addAll(createDamageObject(Types.e_DamageType.NORMAL, 0, myRendingBonus));
                    return output;
                }
            }
            if (myTestForExplodingDamage)
            {
                if (super.doesDiceExplode(diceValue, myExplodingDamageRequirement, myExplodingDamageIsModified))
                {
                    output.addAll(createDamageObject(Types.e_DamageType.NORMAL, Types.diceToNum(myExplodingDamageBonus), 0));
                    return output;
                }
            }
            if (myTestForMortalWounds)
            {
                if (super.doesDiceExplode(diceValue, myMortalWoundsRequirement, myMortalWoundsIsModified))
                {
                    int numMortalWounds = Types.diceToNum(myMortalWoundsBonus);
                    output.addAll(createDamageObject(Types.e_DamageType.MORTAL, numMortalWounds, 0));
                }
            }
            if (myTestForExplodingWounds)
            {
                if (super.doesDiceExplode(diceValue, myExplodingWoundsRequirement, myExplodingWoundsIsModified))
                {
                    for (int i = 0; i < myExplodingWoundsBonus; i++)
                    {
                        output.addAll(createDamageObject(Types.e_DamageType.NORMAL, 0, 0));
                    }
                }
            }
            if (super.doISucceed(diceValue))
            {
                output.addAll(createDamageObject(Types.e_DamageType.NORMAL, 0, 0));
            }
            return output;
        }
    }
}
