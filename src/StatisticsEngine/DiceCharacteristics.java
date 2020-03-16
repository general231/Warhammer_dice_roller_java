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

public class DiceCharacteristics {
    public int numShots = -1;
    public String shotType = "null";
    public int ballisticSkill = -1;
    public boolean autoHit = false;
    public int hitModifier = 0;
    public String hitReRoll = "null";
    public int weaponStrength = -1;
    public int woundModifier = 0;
    public String woundReRoll = "null";
    public int ap = -1;
    public int damageAmount = -1;
    public String damageType = "flat";

    public int toughness = -1;
    public int armourSave = 7;
    public int invulnerableSave = 7;
    public int fnpSave = 7;
    public int woundsCharacteristic = 1;

    public boolean halveDamage = false;
    public boolean reduceDamage1 = false;

    public int explodingHitsRequirement = -1;
    public int explodingHitsBonus = -1;
    public boolean explodingHitsModify = false;
    public int mortalHitsRequirement = -1;
    public boolean mortalHitsModify = false;
    public int autoWoundRequirement = -1;
    public boolean autoWoundModify = false;

    public int explodingDamageRequirement = -1;
    public int explodingDamageBonus = -1;
    public boolean explodingDamageModify = false;
    public int rendingRequirement = -1;
    public int rendingBonus = -1;
    public boolean rendingModify = false;
    public int explodingWoundsRequirement = -1;
    public String explodingWoundsBonus = null;
    public boolean explodingWoundsModify = false;
    public int mortalWoundsRequirement = -1;
    public String mortalWoundsBonus = null;
    public boolean mortalWoundsModify = false;

}
