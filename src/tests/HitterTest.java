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

package tests;

import static org.junit.Assert.*;
import StatisticsEngine.*;
class HitterTestFunctions
{
    public boolean hitterValueTest(Hitter testClass, int diceValue, boolean applyReRoll, Types.e_HitResult expectedOutput)
    {
        return testClass.rollToHit(diceValue, applyReRoll).contains(expectedOutput);
    }
    public boolean hitterQuantityTest(Hitter testClass, int diceValue, boolean applyReRoll, int expectedQuantity)
    {
        return testClass.rollToHit(diceValue, applyReRoll).size() == expectedQuantity;
    }
}

public class HitterTest {
    HitterTestFunctions testFunctions = new HitterTestFunctions();

    // test basic functinality
    public void testBasicHitFunctionality() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{false, false, true, true, true, true};
        Hitter testClass = new Hitter(3);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterValueTest(testClass, diceRolls[i], false, Types.e_HitResult.HIT));
        }
    }
    // test auto success features
    public void testAutoSuccess() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{true, true, true, true, true, true};
        Hitter testClass = new Hitter(3);
        testClass.setAutoSuccess(true);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterValueTest(testClass, diceRolls[i], false, Types.e_HitResult.HIT));
        }
    }
    // test mortal wounding
    public void testMortalWound() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{false, false, false, false, false, true};
        Hitter testClass = new Hitter(3);
        testClass.setMortalWound(6, false);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterValueTest(testClass, diceRolls[i], false, Types.e_HitResult.MORTAL));
        }
    }
    // test auto wounding
    public void testAutoWound() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{false, false, false, false, false, true};
        Hitter testClass = new Hitter(3);
        testClass.setAutoWound(6, false);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterValueTest(testClass, diceRolls[i], false, Types.e_HitResult.WOUND));
        }
    }
    // test exploding hits
    public void testExplodingHits() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{false, false, false, false, false, true};
        Hitter testClass = new Hitter(3);
        testClass.setExplodingHits(6, false, 2);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterQuantityTest(testClass, diceRolls[i], false, 3));
        }
    }
    // test positive to hit modifiers
    public void testPositiveHitModifiers() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{false, true, true, true, true, true};
        Hitter testClass = new Hitter(3);
        testClass.setDiceModifier(1);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterValueTest(testClass, diceRolls[i], false, Types.e_HitResult.HIT));
        }
    }
    // test negative to hit modifiers
    public void testNegativeHitModifiers() {
        int[] diceRolls = new int[]{1,2,3,4,5,6};
        boolean[] expectedOutput = new boolean[]{false, false, false, true, true, true};
        Hitter testClass = new Hitter(3);
        testClass.setDiceModifier(-1);
        for (int i = 0; i < 6; i++)
        {
            assertEquals(expectedOutput[i], testFunctions.hitterValueTest(testClass, diceRolls[i], false, Types.e_HitResult.HIT));
        }
    }
}
