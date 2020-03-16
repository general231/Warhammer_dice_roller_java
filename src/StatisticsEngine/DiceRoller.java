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
