package tests;

import com.company.Types;
import com.company.Wounder;
import com.company.DamageObject;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WounderTestFunctions
{
    public boolean wounderDamageTypeTest(Wounder testWounder, int diceValue, Types.e_HitResult inputType, DamageObject expectedOutput)
    {
        DamageObject testObject = testWounder.rollToWound(inputType, diceValue).get(0);
        boolean output = testObject.getAp() == expectedOutput.getAp();
        output &= testObject.getDamage() == expectedOutput.getDamage();
        output &= testObject.getType() == expectedOutput.getType();
        return output;
    }

    public boolean wounderTestOutputEmpty(Wounder testWounder, int diceValue, Types.e_HitResult inputType)
    {
        return testWounder.rollToWound(inputType, diceValue).isEmpty();
    }

    public boolean woundertestSize(Wounder testWounder, int diceValue, Types.e_HitResult inputType, int size)
    {
        ArrayList<DamageObject> testObjects = testWounder.rollToWound(inputType, diceValue);
        return testObjects.size() == size;
    }
}

public class WounderTest {
    WounderTestFunctions wounderTester = new WounderTestFunctions();

    // test that the right value is being set depending on the toughness
    public void testSuccessValue() {
        Wounder testWounder = new Wounder(8, 4, "1", 1);
        assertEquals(2, testWounder.getSuccessValue());
        testWounder = new Wounder(5, 4, "1", 1);
        assertEquals(3, testWounder.getSuccessValue());
        testWounder = new Wounder(4, 4, "1", 1);
        assertEquals(4, testWounder.getSuccessValue());
        testWounder = new Wounder(3, 4, "1", 1);
        assertEquals(5, testWounder.getSuccessValue());
        testWounder = new Wounder(2, 4, "1", 1);
        assertEquals(6, testWounder.getSuccessValue());
    }

    // test that the damage objects are being correctly created
    public void testDamageCharacteristics() {
        Wounder testWounder = new Wounder(4, 4, "3", 2);
        DamageObject testDamage = new DamageObject(3, 2, Types.e_DamageType.NORMAL);
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 6, Types.e_HitResult.HIT, testDamage));
    }

    // test that the wounder works correctly
    public void testWounderBasics() {
        Wounder testWounder = new Wounder(5, 4, "3", 2);
        DamageObject testDamage = new DamageObject(3, 2, Types.e_DamageType.NORMAL);
        assertTrue(wounderTester.wounderTestOutputEmpty(testWounder, 1, Types.e_HitResult.HIT));
        assertTrue(wounderTester.wounderTestOutputEmpty(testWounder, 2, Types.e_HitResult.HIT));
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 3, Types.e_HitResult.HIT, testDamage));
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 4, Types.e_HitResult.HIT, testDamage));
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 5, Types.e_HitResult.HIT, testDamage));
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 6, Types.e_HitResult.HIT, testDamage));
    }

    // test a hit that auto wounds always results in an output object
    public void testAutoWounding() {
        Wounder testWounder = new Wounder(4, 4, "3", 2);
        DamageObject testDamage = new DamageObject(3, 2, Types.e_DamageType.NORMAL);
        for (int i = 1; i < 7; i++) {
            assertTrue(wounderTester.wounderDamageTypeTest(testWounder, i, Types.e_HitResult.WOUND, testDamage));
        }
    }

    // test that exploding mortal wounds works
    public void testWounderMortalWounds() {
        Wounder testWounder = new Wounder(4, 4, "3", 1);
        DamageObject testDamage = new DamageObject(1, 0, Types.e_DamageType.MORTAL);
        testWounder.setMortalWounds(6, false, "1");
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 6, Types.e_HitResult.HIT, testDamage));
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 1, Types.e_HitResult.MORTAL, testDamage));
    }

    // test extra damage on 6s
    public void testWounderExtraDamage() {
        Wounder testWounder = new Wounder(4, 4, "3", 1);
        DamageObject testDamage = new DamageObject(3, 1, Types.e_DamageType.NORMAL);
        testWounder.setExplodingDamage(6, false, "2");
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 5, Types.e_HitResult.HIT, testDamage));
        testDamage = new DamageObject(5, 1, Types.e_DamageType.NORMAL);
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 6, Types.e_HitResult.HIT, testDamage));
    }

    // test rending on 6s
    public void testWounderRending() {
        Wounder testWounder = new Wounder(4, 4, "3", 1);
        DamageObject testDamage = new DamageObject(3, 1, Types.e_DamageType.NORMAL);
        testWounder.setRending(6, false, 4);
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 5, Types.e_HitResult.HIT, testDamage));
        testDamage = new DamageObject(3, 5, Types.e_DamageType.NORMAL);
        assertTrue(wounderTester.wounderDamageTypeTest(testWounder, 6, Types.e_HitResult.HIT, testDamage));
    }

    // test extra wound on 6s
    public void testExtraWounds()
    {
        Wounder testWounder = new Wounder(4, 4, "3", 1);
        testWounder.setExplodingWounds(6, false, 1);
        int[] expectedOutput = new int[]{0,0,0,1,1,2};
        for (int i = 1; i < 7; i++)
        {
            assertTrue(wounderTester.woundertestSize(testWounder, i, Types.e_HitResult.HIT, expectedOutput[i-1]));
        }
    }

}
