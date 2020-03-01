package GUI;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class OptionsTabPane extends TabPane {

    private Tab attackerTab;
    private Tab defenderTab;

    public OptionsTabPane()
    {
        createAttackerTab();
        createDefenderTab();
        this.getTabs().add(attackerTab);
        this.getTabs().add(defenderTab);
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
        if (jsonString.length() != 0)
        { jsonString.deleteCharAt(jsonString.length()-1); } //remove the last comma
        jsonString.insert(0, "{");
        jsonString.append("}");
        return jsonString.toString();
    }

    private void createAttackerTab()
    {
        GridPane attackerGrid = new GridPane();
        int row = 0;
        attackerGrid.add( new Label("Number of Shots:" ), 0, row );
        attackerGrid.add( new ExtendedTextField("numShots"), 1, row );
        attackerGrid.add( new ExtendedComboBox("shotType", Constants.possibleNumShots), 2, row );
        row++;
        attackerGrid.add( new Label("Ballistic/Weapon Skill: " ), 0, row );
        attackerGrid.add( new ExtendedComboBox( "ballisticSkill", Constants.ballisticSkill, true ),  1, row );
        attackerGrid.add(new ExtendedCheckBox("Auto Hit", "autoHit"), 2, row);
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
        attackerGrid.add(new Label(" " ), 0, 6);
        row++;
        attackerGrid.add(new Label("Armour Penetration: " ), 0, row);
        attackerGrid.add(new ExtendedTextField("ap"), 1, row);
        row++;
        attackerGrid.add(new Label("Damage: " ), 0, row);
        attackerGrid.add(new ExtendedTextField("damageAmount"), 1, row);
        attackerGrid.add(new ExtendedComboBox("damageType", Constants.possibleNumShots, true), 2, row);
        row++;
        attackerGrid.add(new Label("Exploding Hits: " ), 0, row);
        attackerGrid.add(new ExtendedComboBox("explodingHitsRequirement", Constants.ballisticSkillReversed), 1, row);
        attackerGrid.add(new ExtendedComboBox("explodingHitsBonus", Constants.bonus), 2, row);
        attackerGrid.add(new ExtendedCheckBox("Modifable", "explodingHitsModify"), 3, row);
        row++;
        attackerGrid.add(new Label("Mortal Wounds on hit: " ), 0, row);
        attackerGrid.add(new ExtendedComboBox("MortalHitsRequirement", Constants.ballisticSkillReversed), 1, row);
        attackerGrid.add(new ExtendedCheckBox("Modifable", "MortalHitsModify"), 2, row);
        row++;
        attackerGrid.add(new Label("Auto Wound: " ), 0, row);
        attackerGrid.add(new ExtendedComboBox("autoWoundRequirement", Constants.ballisticSkillReversed), 1, row);
        attackerGrid.add(new ExtendedCheckBox("Modifable", "autoWoundModify"), 2, row);
        row++;
        attackerGrid.add(new Label(" " ), 0, row);
        row++;
        attackerGrid.add(new Label("Exploding Damage: " ), 0, row);
        row++;
        attackerGrid.add(new Label("Rending: " ), 0, row);
        row++;
        attackerGrid.add(new Label("Exploding Wounds: " ), 0, row);
        row++;
        attackerGrid.add(new Label("Mortal Wounds: " ), 0, row);
//        row++;

        attackerTab = new Tab("Attacker");
        attackerTab.setContent(attackerGrid);
    }
    private void createDefenderTab() {
        GridPane defenderGrid = new GridPane();
        int row = 0;
        defenderGrid.add(new Label("Toughness:"), 0, row);
        defenderGrid.add(new ExtendedTextField("toughness"), 1, row);
        row++;
        defenderGrid.add(new Label("Armour Save:"), 0, row);
        defenderGrid.add(new ExtendedComboBox("armourSave", Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Invulnerable Save:"), 0, row);
        defenderGrid.add(new ExtendedComboBox("invunerableSave", Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Feel No Pain Save:"), 0, row);
        defenderGrid.add(new ExtendedComboBox("fnpSave", Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Wounds Characteristic:"), 0, row);
        defenderGrid.add(new ExtendedTextField("woundCharacteristic"), 1, row);
//        row++;

        defenderTab = new Tab("Defender");
        defenderTab.setContent(defenderGrid);
    }
}
