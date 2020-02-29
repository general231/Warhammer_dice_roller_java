package StatisticsEngine;

public class DiceRollFactory {
    static public DiceObject createDiceObject(int numDice, String stringDiceObject)
    { //" ", "XD3", "XD6", "XD6 pick the highest", "XD6 with re-roll", "XD3 with re-roll"
        switch (stringDiceObject) {
            case "XD3":
                return new XD3DiceObject(numDice);
            case "XD6":
                return new XD6DiceObject(numDice);
            case "XD6 pick the highest":
                return new bestOfDiceD6Object(numDice);
            case "XD3 pick the highest":
                return new bestOfDiceD3Object(numDice);
            case "XD6 with re-roll":
                return new XD6WithReRollDiceObject(numDice);
            case "XD3 with re-roll":
                return new XD3WithReRollDiceObject(numDice);
            default:
                return new flatDamageDiceObject(numDice);
        }
    }
}


class flatDamageDiceObject implements DiceObject
{
    private int myFlatAmount;

    public flatDamageDiceObject(int value)
    {
        myFlatAmount = value;
    }
    @Override
    public Integer rollDice() {
        return myFlatAmount;
    }
}

class XD3WithReRollDiceObject implements DiceObject
{
    private int myNumDice;
    private DiceRoller myDiceRoller;

    public XD3WithReRollDiceObject(int numDice)
    {
        myNumDice = numDice;
        myDiceRoller = new DiceRoller();
    }

    @Override
    public Integer rollDice() {
        int temp = 0;
        for (int i = 0; i < myNumDice; i++)
        {
            int diceRoll = myDiceRoller.rollDiceD3();
            // reroll any dice under 4 as the average dice roll is 3.5 for D6
            temp += diceRoll > 1 ? diceRoll : myDiceRoller.rollDiceD3();
        }
        return temp;
    }
}

class XD6WithReRollDiceObject implements DiceObject
{
    private int myNumDice;
    private DiceRoller myDiceRoller;

    public XD6WithReRollDiceObject(int numDice)
    {
        myNumDice = numDice;
        myDiceRoller = new DiceRoller();
    }

    @Override
    public Integer rollDice() {
        int temp = 0;
        for (int i = 0; i < myNumDice; i++)
        {
            int diceRoll = myDiceRoller.rollDiceD6();
            // reroll any dice under 4 as the average dice roll is 3.5 for D6
            temp += diceRoll > 3 ? diceRoll : myDiceRoller.rollDiceD6();
        }
        return temp;
    }
}

class XD3DiceObject implements DiceObject
{
    private int myNumDice;
    private DiceRoller myDiceRoller;
    public XD3DiceObject(int numDice)
    {
        myNumDice = numDice;
        myDiceRoller = new DiceRoller();
    }
    @Override
    public Integer rollDice() {
        int temp = 0;
        for (int i = 0; i < myNumDice; i++)
        {
            temp += myDiceRoller.rollDiceD3();
        }
        return temp;
    }
}

class XD6DiceObject implements DiceObject
{
    private int myNumDice;
    private DiceRoller myDiceRoller;
    public XD6DiceObject(int numDice)
    {
        myNumDice = numDice;
        myDiceRoller = new DiceRoller();
    }
    @Override
    public Integer rollDice() {
        int temp = 0;
        for (int i = 0; i < myNumDice; i++)
        {
            temp += myDiceRoller.rollDiceD6();
        }
        return temp;
    }
}

class bestOfDiceD6Object implements DiceObject
{
    private int myNumDice;
    private DiceRoller myDiceRoller;

    public bestOfDiceD6Object(int numDice)
    {
        myNumDice = numDice;
        myDiceRoller = new DiceRoller();
    }

    @Override
    public Integer rollDice() {
        int temp = myDiceRoller.rollDiceD6();
        for (int i = 1; i < myNumDice; i++)
        {
            temp = Math.max(temp, myDiceRoller.rollDiceD6());
        }
        return temp;
    }
}

class bestOfDiceD3Object implements DiceObject
{
    private int myNumDice;
    private DiceRoller myDiceRoller;

    public bestOfDiceD3Object(int numDice)
    {
        myNumDice = numDice;
        myDiceRoller = new DiceRoller();
    }

    @Override
    public Integer rollDice() {
        int temp = myDiceRoller.rollDiceD3();
        for (int i = 1; i < myNumDice; i++)
        {
            temp = Math.max(temp, myDiceRoller.rollDiceD3());
        }
        return temp;
    }
}