package GUI;
import StatisticsEngine.StatisticsEngine;
import StatisticsEngine.StatisticsStorageObject;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;


public class StatisticsTab extends Tab{
    private HorizontalStatsDisplay myShotsDisplay;
    private HorizontalStatsDisplay myHitsDisplay;
    private HorizontalStatsDisplay myWoundsDisplay;
    private HorizontalStatsDisplay myDeadModelsDisplay;
    private HorizontalStatsDisplay myDamageRecievedDisplay;
    private Label myAverageHitSuccessRate;
    private Label myAverageWoundSuccessRate;
    private DecimalFormat myFormatter;

    public StatisticsTab(String tabName)
    {
        this.setText(tabName);
        myShotsDisplay = new HorizontalStatsDisplay("Number of Shots");
        myHitsDisplay = new HorizontalStatsDisplay("Successful Hits");
        myWoundsDisplay = new HorizontalStatsDisplay("Successful Wounds");
        myDeadModelsDisplay = new HorizontalStatsDisplay("Number of dead models");
        myDamageRecievedDisplay = new HorizontalStatsDisplay("Damage Dealt");
        myFormatter = new DecimalFormat("##.##");
        myAverageHitSuccessRate = new Label("0");
        myAverageWoundSuccessRate = new Label("0");
        Label hitPercentLabel = new Label("Success rate(%): ");
        Label woundPercentLabel = new Label("      Wound Rate(%): ");
        HBox hBox = new HBox(hitPercentLabel, myAverageHitSuccessRate, woundPercentLabel, myAverageWoundSuccessRate);
        hBox.setSpacing(10);
        VBox display = new VBox();
        display.setSpacing(12);
        display.getChildren().addAll(myShotsDisplay, myHitsDisplay, myWoundsDisplay, myDeadModelsDisplay, myDamageRecievedDisplay, hBox);
        display.setSpacing(5);
        this.setContent(display);
    }

    public void updateDisplay(StatisticsStorageObject newStats)
    {
        myShotsDisplay.updateValues(newStats.numberOfShots);
        myHitsDisplay.updateValues(newStats.numberOfHits);
        myWoundsDisplay.updateValues(newStats.numberOfWounds);
        myDeadModelsDisplay.updateValues(newStats.numberOfModelsLost);
        myDamageRecievedDisplay.updateValues(newStats.numberOfDamageRecieved);
        Double successRatePercent = 100 * (newStats.numberOfHits.getMean() / newStats.numberOfShots.getMean());
        Double woundRatePercent = 100 * (newStats.numberOfWounds.getMean() / newStats.numberOfHits.getMean());
        myAverageHitSuccessRate.setText(myFormatter.format(successRatePercent));
        myAverageWoundSuccessRate.setText(myFormatter.format(woundRatePercent));


    }
}
