package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class StatisticsTabPane extends TabPane {
    private Tab deadModelsTab;
    private Tab damageRecievedTab;
    private Tab statisticsTab;

    public StatisticsTabPane()
    {
        createDeadModelsTab();
        createDamageRecievedTab();
        createStatisticsTab();
        this.getTabs().add(damageRecievedTab);
        this.getTabs().add(deadModelsTab);
        this.getTabs().add(statisticsTab);
        this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    private void createDeadModelsTab()
    {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("A1"));
        vBox.getChildren().add(new Label("B2"));
        deadModelsTab = new Tab("Dead Models");
        deadModelsTab.setContent(vBox);
    }

    private void createDamageRecievedTab()
    {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("A1"));
        vBox.getChildren().add(new Label("B2"));
        damageRecievedTab = new Tab("Damage Dealt");
        damageRecievedTab.setContent(vBox);
    }

    private void createStatisticsTab()
    {
        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("A1"));
        vBox.getChildren().add(new Label("B2"));
        statisticsTab = new Tab("Statistics");
        statisticsTab.setContent(vBox);
    }

}
