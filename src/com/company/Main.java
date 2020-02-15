package com.company;

import java.util.ArrayList;
import com.company.Types;
import com.company.Hitter;
import tests.HitterTest;
import tests.WounderTest;

public class Main {

    public static void main(String[] args) {
	SuccessObject test = new SuccessObject(8);
	DiceRoller dice = new DiceRoller();
    Hitter aHitter = new Hitter(3);
    if (aHitter.rollToHit(3).contains(Types.e_HitResult.HIT))
    {
        System.out.println("Pass");
    }
    else
    {
        System.out.println("Fail");
    }

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
    }
}
