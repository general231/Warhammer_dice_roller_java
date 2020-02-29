package StatisticsEngine;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatisticsEngine {
    private Hitter myHitter;
    private Wounder myWounder;
    private Saver mySaver;
    private DiceCharacteristics myDiceCharacteristics;

    public StatisticsEngine(String jsonObject)
    {
        Gson gson = new Gson();
        System.out.println(jsonObject);
        myDiceCharacteristics = gson.fromJson(jsonObject, DiceCharacteristics.class);
        System.out.println(myDiceCharacteristics.ballisticSkill);
        System.out.println(myDiceCharacteristics.hitModifier);
    }
}
