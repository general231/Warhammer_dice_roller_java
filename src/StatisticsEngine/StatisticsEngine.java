package StatisticsEngine;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class StatisticsEngine {
    private Hitter myHitter;
    private Wounder myWounder;
    private Saver mySaver;
    private DiceCharacteristics myDiceCharacteristics;
    private DiceObject myShots;
    private DiceRoller myDiceRoller;
    private Integer myNumIterations;

    public StatisticsEngine(String jsonObject, int numIterations)
    {
        Gson gson = new Gson();
        System.out.println(jsonObject);
        myDiceCharacteristics = gson.fromJson(jsonObject, DiceCharacteristics.class);
        myShots = DiceRollFactory.createDiceObject(myDiceCharacteristics.numShots, myDiceCharacteristics.shotType);
        myNumIterations = numIterations;
        myDiceRoller = new DiceRoller();
        createHitterObject();
        createWounderObject();
        createSaverObject();
    }

    public StatisticsStorageObject executeOrder()
    {
        StatisticsStorageObject output = new StatisticsStorageObject();
        for (int i = 0; i < myNumIterations; i++) {
            ArrayList<Integer> shots = rollHitDice();
            output.numberOfShots.addValue(shots.size());
            ArrayList<Types.e_HitResult> hits = new ArrayList<>();
            for (Integer value : shots)
            {
                hits.addAll(myHitter.rollToHit(value));
            }
            output.numberOfHits.addValue(hits.size());
            ArrayList<DamageObject> wounds = new ArrayList<DamageObject>();
            for (Types.e_HitResult value : hits)
            {
                wounds.addAll(myWounder.rollToWound(value, myDiceRoller.rollDiceD6()));
            }
            output.numberOfWounds.addValue(wounds.size());
            int passedSaves = 0;
            for (DamageObject value : wounds)
            {
                if (mySaver.rollToSave(value))
                {
                    passedSaves++;
                }
            }
            output.numberOfSaves.addValue(passedSaves);
            output.numberOfModelsLost.addValue(mySaver.getLostModels());
            output.numberOfDamageRecieved.addValue(mySaver.getDamageReceived());
        }

        return output;
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

    private ArrayList<Integer> rollHitDice()
    {
        ArrayList<Integer> output = new ArrayList<>();
        int numberOfShots = myShots.rollDice();
        for (int i = 0; i < numberOfShots; i++)
        {
            output.add(myDiceRoller.rollDiceD6());
        }
        return output;
    }

    static public void checkForRequriedFields(String jsonString)
    {
        Gson gson = new Gson();
        System.out.println(jsonString);
        DiceCharacteristics checkingObject = gson.fromJson(jsonString, DiceCharacteristics.class);
        //num shots, balistic skill, strength, toughness, damage
        if (checkingObject.numShots == -1)
        {
            throw new IllegalArgumentException("Number of shots needs to be set");
        }
        else if (checkingObject.ballisticSkill == -1)
        {
            throw new IllegalArgumentException("Balistic skill needs to be set");
        }
        else if (checkingObject.weaponStrength == -1)
        {
            throw new IllegalArgumentException("Weapon skill needs to be set");
        }
        else if (checkingObject.toughness == -1)
        {
            throw new IllegalArgumentException("Toughness needs to be set");
        }
        else if (checkingObject.damageAmount == -1)
        {
            throw new IllegalArgumentException("Damage needs to be set");
        }
    }
}
