package GUI;

import StatisticsEngine.StatisticsStorageObject;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.NumberAxis;

public class StatisticsTabPane extends TabPane {
    private GraphTab deadModelsTab;
    private GraphTab damageRecievedTab;
    private StatisticsTab statisticsTab;

    public StatisticsTabPane()
    {
        deadModelsTab = new GraphTab("Dead Models");
        damageRecievedTab = new GraphTab("Damage Dealt");
        statisticsTab = new StatisticsTab("Statistics");
        this.getTabs().add(damageRecievedTab);
        this.getTabs().add(deadModelsTab);
        this.getTabs().add(statisticsTab);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }


    public void update(StatisticsStorageObject newStats)
    {
        statisticsTab.updateDisplay(newStats);
        deadModelsTab.updateDisplay(newStats.numberOfModelsLost);
        damageRecievedTab.updateDisplay(newStats.numberOfDamageRecieved);
    }
}
