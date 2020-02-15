package tests;

import com.company.Types;
import com.company.Wounder;
import com.company.DamageObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

public class WounderTest {

    public void testSuccessValue()
    {
        Wounder testWounder = new Wounder(8, 4, 1, 1);
        assertEquals(2, testWounder.getSuccessValue());
        testWounder = new Wounder(5, 4, 1, 1);
        assertEquals(3, testWounder.getSuccessValue());
        testWounder = new Wounder(4, 4, 1, 1);
        assertEquals(4, testWounder.getSuccessValue());
        testWounder = new Wounder(3, 4, 1, 1);
        assertEquals(5, testWounder.getSuccessValue());
        testWounder = new Wounder(2, 4, 1, 1);
        assertEquals(6, testWounder.getSuccessValue());
    }
}
