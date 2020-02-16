package GUI;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class OptionsTabPane extends TabPane {

    private Tab attackerTab;
    private Tab defenderTab;
//    private TabPane

    public OptionsTabPane()
    {
        createAttackerTab();
        createDefenderTab();
        this.getTabs().add(attackerTab);
        this.getTabs().add(defenderTab);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    private void createAttackerTab()
    {
        GridPane attackerGrid = new GridPane();
        int row = 0;
        attackerGrid.add(new Label("Number of Shots:" ), 0, row);
        attackerGrid.add(new TextField(), 1, row);
        attackerGrid.add(createComboBox(Constants.possibleDiceRolls), 2, row);
        row++;
        attackerGrid.add(new Label("Ballistic/Weapon Skill: "), 0, row);
        attackerGrid.add( createComboBox(Constants.ballisticSkill),  1, row);
        row++;
        attackerGrid.add(new Label("Hit Modifier: " ), 0, row);
        attackerGrid.add( createComboBox(Constants.hitModifiers),  1, row);
        row++;
        attackerGrid.add(new Label("Hit Re-Rolls: " ), 0, row);
        attackerGrid.add( createComboBox(Constants.hitReRolls),  1, row);
        row++;
        attackerGrid.add(new Label("Weapon Strength: " ), 0, row);
        attackerGrid.add(new TextField(), 1, row);
        row++;
        attackerGrid.add(new Label("Wound Modifier: " ), 0, row);
        attackerGrid.add( createComboBox(Constants.hitModifiers),  1, row);
        row++;
        attackerGrid.add(new Label("Wound Re-Roll: " ), 0, row);
        attackerGrid.add( createComboBox(Constants.woundReRolls),  1, row);
        attackerGrid.add(new Label(" " ), 0, 6);
        row++;
        attackerGrid.add(new Label("Armour Penetration: " ), 0, row);
        attackerGrid.add(new TextField(), 1, row);
        row++;
        attackerGrid.add(new Label("Exploding Hits: " ), 0, row);
        row++;
        attackerGrid.add(new Label("Mortal Wounds on hit: " ), 0, row);
        row++;
        attackerGrid.add(new Label("Auto Wound: " ), 0, row);
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
        row++;




        attackerTab = new Tab("Attacker");
        attackerTab.setContent(attackerGrid);
    }
    private void createDefenderTab()
    {
        GridPane defenderGrid = new GridPane();
        int row = 0;
        defenderGrid.add(new Label("Toughness:" ), 0, row);
        defenderGrid.add(new TextField(), 1, row);
        row++;
        defenderGrid.add(new Label("Armour Save:"), 0, row);
        defenderGrid.add(createComboBox(Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Invulnerable Save:"), 0, row);
        defenderGrid.add(createComboBox(Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Feel No Pain Save:"), 0, row);
        defenderGrid.add(createComboBox(Constants.saveValues), 1, row);
        row++;
        defenderGrid.add(new Label("Wounds Characteristic:"), 0, row);
        defenderTab = new Tab("Defender");
        defenderTab.setContent(defenderGrid);
    }

    private ComboBox<Integer> createComboBox(int[] values)
    {
        ComboBox<Integer> cBox = new ComboBox<>();
        for (int num : values)
        {
            cBox.getItems().add(num);
        }
        return cBox;
    }

    private ComboBox<String> createComboBox(String[] values)
    {
        ComboBox<String> cBox = new ComboBox<String>();
        for (String string : values)
        {
            cBox.getItems().add(string);
        }
        return cBox;
    }

}
