package StatisticsEngine;

import java.util.Random;

public class DiceRoller {
    Random myRandom;

    public DiceRoller()
    {
        myRandom = new Random();
    }

    public int rollDiceD6()
    {
        return (int)(1 + 6 * myRandom.nextFloat());
    }

    public int rollDiceD3()
    {
        return (int)(1 + 3 * myRandom.nextFloat());
    }
}
