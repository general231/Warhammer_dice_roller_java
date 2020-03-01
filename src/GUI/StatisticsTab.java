package GUI;
import StatisticsEngine.StatisticsEngine;
import StatisticsEngine.StatisticsStorageObject;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;


public class StatisticsTab extends Tab{
    private HorizontalStatsDisplay myShotsDisplay;
    private HorizontalStatsDisplay myHitsDisplay;
    private HorizontalStatsDisplay myWoundsDisplay;
    private HorizontalStatsDisplay myDeadModelsDisplay;
    private HorizontalStatsDisplay myDamageRecievedDisplay;
    private Label myAverageHitSuccessRate;
    private Label myAverageWoundSuccessRate;

    public StatisticsTab(String tabName)
    {
        this.setText(tabName);
        myShotsDisplay = new HorizontalStatsDisplay("Number of Shots");
        myHitsDisplay = new HorizontalStatsDisplay("Successful Hits");
        myWoundsDisplay = new HorizontalStatsDisplay("Successful Wounds");
        myDeadModelsDisplay = new HorizontalStatsDisplay("Number of dead models");
        myDamageRecievedDisplay = new HorizontalStatsDisplay("Damage Dealt");
        VBox display = new VBox();
        display.getChildren().addAll(myShotsDisplay, myHitsDisplay, myWoundsDisplay, myDeadModelsDisplay, myDamageRecievedDisplay);
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
    }
}
