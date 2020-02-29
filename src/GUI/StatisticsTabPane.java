package GUI;

import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.NumberAxis;

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
        NumberAxis xAxis = new NumberAxis(0,10,1);
        NumberAxis yAxis = new NumberAxis(0,1,0.1);
        LineChart myChart = new LineChart(xAxis, yAxis);
        xAxis.setLabel("Damage");
        yAxis.setLabel("Probability");
        XYChart.Series series = new XYChart.Series();
        series.setName("Test");
        series.getData().add(new XYChart.Data(0, 0.8));
        series.getData().add(new XYChart.Data(1, 0.6));
        series.getData().add(new XYChart.Data(2, 0.99));
        series.getData().add(new XYChart.Data(3, 0.1));
        series.getData().add(new XYChart.Data(6, 0));
        myChart.getData().addAll(series);
        vBox.getChildren().add(myChart);

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
