package StatisticsEngine;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatisticsEngine {
    private Hitter myHitter;
    private Wounder myWounder;
    private Saver mySaver;
    private DiceCharacteristics myDiceCharacteristics;
    private Integer myNumShots;
    private DiceObject myShotType;
    private Integer myNumIterations;

    public StatisticsEngine(String jsonObject, int numIterations)
    {
        Gson gson = new Gson();
        System.out.println(jsonObject);
        myDiceCharacteristics = gson.fromJson(jsonObject, DiceCharacteristics.class);
        myNumIterations = numIterations;
        createHitterObject();
        createWounderObject();
        createSaverObject();
    }

    private void createHitterObject()
    {
        myHitter = new Hitter(myDiceCharacteristics.ballisticSkill);
        myHitter.setDiceModifier(myDiceCharacteristics.hitModifier);
        myHitter.setReRoll(Types.stringToEReRoll(myDiceCharacteristics.hitReRoll));
    }

    private void createWounderObject()
    {
        myWounder = new Wounder(myDiceCharacteristics.weaponStrength, myDiceCharacteristics.toughness,
                DiceRollFactory.createDiceObject(myDiceCharacteristics.damageAmount, myDiceCharacteristics.damageType),
                myDiceCharacteristics.ap);
        myWounder.setDiceModifier(myDiceCharacteristics.woundModifier);
        myWounder.setReRoll(Types.stringToEReRoll(myDiceCharacteristics.woundReRoll));
    }

    private void createSaverObject()
    {
        mySaver = new Saver(myDiceCharacteristics.armourSave, myDiceCharacteristics.invunerableSave,
                1, myDiceCharacteristics.fnpSave);
    }

    static public void checkForRequriedFields(String jsonString)
    {
        Gson gson = new Gson();
        System.out.println(jsonString);
        DiceCharacteristics checkingObject = gson.fromJson(jsonString, DiceCharacteristics.class);
        //num shots, balistic skill, strength, toughness, damage
        if (checkingObject.numShots == 0)
        {
            throw new IllegalArgumentException("Number of shots needs to be set");
        }
        else if (checkingObject.ballisticSkill == 0)
        {
            throw new IllegalArgumentException("Balistic skill needs to be set");
        }
        else if (checkingObject.weaponStrength == 0)
        {
            throw new IllegalArgumentException("Weapon skill needs to be set");
        }
        else if (checkingObject.toughness == 0)
        {
            throw new IllegalArgumentException("Toughness needs to be set");
        }
        else if (checkingObject.damageAmount == 0)
        {
            throw new IllegalArgumentException("Damage needs to be set");
        }
    }
}
