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
    static public DiceObject createDiceObject(String stringDiceObject)
    { //" ", "XD3", "XD6", "XD6 pick the highest", "XD6 with re-roll", "XD3 with re-roll"
        switch (stringDiceObject) {
            case "1D3":
                return new XD3DiceObject(1);
            case "2D3":
                return new XD3DiceObject(2);
            case "3D3":
                return new XD3DiceObject(3);
            case "1D6":
                return new XD6DiceObject(1);
            case "2D6":
                return new XD6DiceObject(2);
            case "3D6":
                return new XD6DiceObject(3);
            case "2D6 pick the highest":
                return new bestOfDiceD6Object(2);
            case "1D6 with re-roll":
                return new XD6WithReRollDiceObject(1);
            case "1D3 with re-roll":
                return new XD3WithReRollDiceObject(1);
            case "1":
                return new flatDamageDiceObject(1);
            case "2":
                return new flatDamageDiceObject(2);
            case "3":
                return new flatDamageDiceObject(3);
            case "4":
                return new flatDamageDiceObject(4);
            case "5":
                return new flatDamageDiceObject(5);
            case "6":
                return new flatDamageDiceObject(6);
        }
        return null;
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