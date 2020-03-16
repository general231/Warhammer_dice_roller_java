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

package GUI;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class OptionsTabPane extends TabPane {

    private Tab attackerTab;
    private Tab defenderTab;
    private Tab optionsTab;

    public OptionsTabPane()
    {
        createAttackerTab();
        createDefenderTab();
        createOptionsTab();
        this.getTabs().add(attackerTab);
        this.getTabs().add(defenderTab);
        this.getTabs().add(optionsTab);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    public String getData()
    {
        StringBuilder jsonString = new StringBuilder();
        GridPane gridPane = (GridPane) attackerTab.getContent();
        for (Node component : gridPane.getChildren())
        {
            if (GridPane.getColumnIndex(component) != 0)
            {
                ExtendedNodeInterface extendedComponent = (ExtendedNodeInterface)component;
                jsonString.append(extendedComponent.getData());
            }
        }
        gridPane = (GridPane) defenderTab.getContent();
        for (Node component : gridPane.getChildren())
        {
            if (GridPane.getColumnIndex(component) != 0)
            {
                ExtendedNodeInterface extendedComponent = (ExtendedNodeInterface)component;
                jsonString.append(extendedComponent.getData());
            }
        }
        gridPane = (GridPane) optionsTab.getContent();
        for (Node component : gridPane.getChildren())
        {
            if (GridPane.getColumnIndex(component) != 0)
            {
                ExtendedNodeInterface extendedComponent = (ExtendedNodeInterface)component;
                jsonString.append(extendedComponent.getData());
            }
        }
        if (jsonString.length() != 0)
        { jsonString.deleteCharAt(jsonString.length()-1); } //remove the last comma
        jsonString.insert(0, "{");
        jsonString.append("}");
        return jsonString.toString();
    }

    private void createAttackerTab()
    {
        GridPane attackerGrid = new GridPane();
        attackerGrid.setHgap(5);
        attackerGrid.setVgap(3);
        int row = 0;
        attackerGrid.add( new Label("Number of Shots:" ), 0, row );
        attackerGrid.add( new ExtendedTextField("numShots"), 1, row );
        row++;
        attackerGrid.add( new ExtendedComboBox("shotType", Constants.possibleNumShots), 1, row );
        row++;
        attackerGrid.add( new Label("Ballistic/Weapon Skill: " ), 0, row );
        attackerGrid.add( new ExtendedComboBox( "ballisticSkill", Constants.ballisticSkill, true ),  1, row );
        row++;
        attackerGrid.add(new ExtendedCheckBox("Auto Hit", "autoHit"), 1, row);
        row++;
        attackerGrid.add( new Label("Hit Modifier: " ), 0, row );
        attackerGrid.add( new ExtendedComboBox("hitModifier", Constants.hitModifiers),  1, row);
        row++;
        attackerGrid.add( new Label("Hit Re-Rolls: " ), 0, row);
        attackerGrid.add( new ExtendedComboBox( "hitReRoll", Constants.hitReRolls),  1, row);
        row++;
        attackerGrid.add( new Label("Weapon Strength: " ), 0, row);
        attackerGrid.add( new ExtendedTextField("weaponStrength"), 1, row);
        row++;
        attackerGrid.add(new Label("Wound Modifier: " ), 0, row);
        attackerGrid.add( new ExtendedComboBox("woundModifier", Constants.hitModifiers),  1, row);
        row++;
        attackerGrid.add(new Label("Wound Re-Roll: " ), 0, row);
        attackerGrid.add( new ExtendedComboBox("woundReRoll", Constants.woundReRolls),  1, row);
        row++;
        attackerGrid.add(new Label("Armour Penetration: " ), 0, row);
        attackerGrid.add(new ExtendedTextField("ap"), 1, row);
        row++;
        attackerGrid.add(new Label("Damage: " ), 0, row);
        attackerGrid.add(new ExtendedTextField("damageAmount"), 1, row);
        row++;
        attackerGrid.add(new ExtendedComboBox("damageType", Constants.possibleNumShots, true), 1, row);
//        row++;

        attackerTab = new Tab("Attacker");
        attackerTab.setContent(attackerGrid);
    }
    private void createDefenderTab() {
        GridPane defenderGrid = new GridPane();
        defenderGrid.setHgap(5);
        defenderGrid.setVgap(3);
        int row = 0;
        defenderGrid.add(new Label("Toughness:"), 0, row);
        defenderGrid.add(new ExtendedTextField("toughness"), 1, row);
        row++;
        defenderGrid.add(new Label("Armour Save:"), 0, row);
        defenderGrid.add(new ExtendedComboBox("armourSave", Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Invulnerable Save:"), 0, row);
        defenderGrid.add(new ExtendedComboBox("invulnerableSave", Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Feel No Pain Save:"), 0, row);
        defenderGrid.add(new ExtendedComboBox("fnpSave", Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Wounds Characteristic:"), 0, row);
        defenderGrid.add(new ExtendedTextField("woundsCharacteristic"), 1, row);
//        row++;
        defenderTab = new Tab("Defender");
        defenderTab.setContent(defenderGrid);
    }

    private void createOptionsTab()
    {
        GridPane optionsGrid = new GridPane();
        optionsGrid.setHgap(5);
        optionsGrid.setVgap(3);
        int row = 0;
        optionsGrid.add(new Label("Halve Damage: " ), 0, row);
        optionsGrid.add(new ExtendedCheckBox("", "halveDamage"), 1, row);
        row++;
        optionsGrid.add(new Label("Reduce Damage by 1: "), 0, row);
        optionsGrid.add(new ExtendedCheckBox("", "reduceDamage1"), 1, row);
        row++;
        optionsGrid.add(new Label("Exploding Hits: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("explodingHitsRequirement", Constants.ballisticSkillReversed, "Hit Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "explodingHitsModify"), 2, row);
        row++;
        optionsGrid.add(new ExtendedComboBox("explodingHitsBonus", Constants.bonus, "Bonus"), 1, row);
        row++;
        optionsGrid.add(new Label("Mortal Wound on hit: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("mortalHitsRequirement", Constants.ballisticSkillReversed, "Hit Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "mortalHitsModify"), 2, row);
        row++;
        optionsGrid.add(new Label("Auto Wound: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("autoWoundRequirement", Constants.ballisticSkillReversed, "Hit Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "autoWoundModify"), 2, row);
        row++;
        optionsGrid.add(new Label(" " ), 0, row);
        row++;
        optionsGrid.add(new Label("Exploding Damage: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("explodingDamageRequirement", Constants.ballisticSkillReversed, "Wound Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "explodingDamageModify"), 2, row);
        row++;
        optionsGrid.add(new ExtendedComboBox("explodingDamageBonus", Constants.bonus, "Bonus"), 1, row);
        row++;
        optionsGrid.add(new Label("Improve AP: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("rendingRequirement", Constants.ballisticSkillReversed, "Wound Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "rendingModify"), 2, row);
        row++;
        optionsGrid.add(new ExtendedComboBox("rendingBonus", Constants.bonus, "Bonus"), 1, row);
        row++;
        optionsGrid.add(new Label("Exploding Wounds: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("explodingWoundsRequirement", Constants.ballisticSkillReversed, "Wound Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "explodingWoundsModify"), 2, row);
        row++;
        optionsGrid.add(new ExtendedComboBox("explodingWoundsBonus", Constants.bonus, "Bonus"), 1, row);
        row++;
        row++;
        optionsGrid.add(new Label("Mortal Wounds: " ), 0, row);
        optionsGrid.add(new ExtendedComboBox("mortalWoundsRequirement", Constants.ballisticSkillReversed, "Wound Roll"), 1, row);
        optionsGrid.add(new ExtendedCheckBox("Modifiable", "mortalWoundsModify"), 2, row);
        row++;
        optionsGrid.add(new ExtendedComboBox("mortalWoundsBonus", Constants.bonus, "Bonus"), 1, row);
//        row++;

        optionsTab = new Tab("Options");
        optionsTab.setContent(optionsGrid);
    }
}
