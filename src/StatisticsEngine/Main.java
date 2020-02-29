package StatisticsEngine;

import tests.HitterTest;
import tests.WounderTest;

public class Main {

    public static void main(String[] args) {

    HitterTest myHitterTestCases = new HitterTest();
    myHitterTestCases.testBasicHitFunctionality();
    myHitterTestCases.testAutoSuccess();
    myHitterTestCases.testAutoWound();
    myHitterTestCases.testMortalWound();
    myHitterTestCases.testExplodingHits();
    myHitterTestCases.testPositiveHitModifiers();
    myHitterTestCases.testNegativeHitModifiers();

    WounderTest myWounderTestCases = new WounderTest();
    myWounderTestCases.testSuccessValue();
    myWounderTestCases.testDamageCharacteristics();
    myWounderTestCases.testWounderBasics();
    myWounderTestCases.testAutoWounding();
    myWounderTestCases.testWounderMortalWounds();
    myWounderTestCases.testWounderExtraDamage();
    myWounderTestCases.testWounderRending();
    myWounderTestCases.testExtraWounds();
    }
}
