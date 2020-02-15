package com.company;

import java.util.Random;

public class DiceRoller {
    Random myRandom;
    int myMaxValue;

    public DiceRoller()
    {
        myRandom = new Random();
    }

    public int rollDiceD6()
    {
        return (int)(1 + 6 * Math.random());
    }

    public int rollDiceD3()
    {
        return (int)(1 + 3 * Math.random());
    }
}
