package GUI;

import StatisticsEngine.StatisticsEngine;
import StatisticsEngine.StatisticsStorageObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.Collections;

public class GraphTab extends Tab{
    private AreaChart<Integer, Double> myPDF;
    private LineChart<Integer, Double> myCDF;
    private CheckBox myHoldDataCheckBox;
    private Button myViewButton;
    private Integer mySeriesCounter = 0;
    private Boolean visibility = true;

    public GraphTab(String displayName)
    {
        this.setText(displayName);
        NumberAxis yAxisP = new NumberAxis();
        NumberAxis xAxisP = new NumberAxis();
        NumberAxis yAxisC = new NumberAxis();
        NumberAxis xAxisC = new NumberAxis();
        yAxisP.setLabel("Percentage"); yAxisC.setLabel("Percentage");
        xAxisC.setLabel(displayName); xAxisP.setLabel(displayName);

        myPDF = new AreaChart(xAxisP, yAxisP);
        myCDF = new LineChart(xAxisC, yAxisC);

        StackPane stackPane = new StackPane();
        myViewButton = new Button("View PDF");
        myHoldDataCheckBox = new CheckBox("Hold");
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(myViewButton, myHoldDataCheckBox);
        hBox.setMaxHeight(Constants.toolbarHeight);
        stackPane.getChildren().addAll(myCDF, myPDF);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(stackPane, hBox);
        this.setContent(vBox);
        myCDF.setVisible(visibility);
        myPDF.setVisible(!visibility);
        visibility = ! visibility;

        myViewButton.setOnAction(e -> {

            myCDF.setVisible(visibility);
            myPDF.setVisible(!visibility);
            visibility = ! visibility;
            if (!visibility)
            {
                myViewButton.setText("View PDF");
            }
            else
            {
                myViewButton.setText("View CDF");
            }
        });
    }

    public void updateDisplay(DescriptiveStatistics newStats)
    {
        ArrayList<Double> dataPoints = new ArrayList<Double>(Collections.nCopies((int)newStats.getMax()+1, 0.0));

        for (int i = 0; i < newStats.getN(); i++)
        {
            int index = (int) newStats.getElement(i);
            dataPoints.set(index, dataPoints.get(index) + 1);
        }
        for (int i = 0; i < dataPoints.size(); i++)
        { // convert to percentage
            dataPoints.set(i, 100 * dataPoints.get(i) /  newStats.getN());
        }

        XYChart.Series<Integer, Double> pdfSeries = new XYChart.Series<Integer, Double>();
        XYChart.Series<Integer, Double> cdfSeries = new XYChart.Series<Integer, Double>();
        pdfSeries.setName(String.valueOf(mySeriesCounter));
        cdfSeries.setName(String.valueOf(mySeriesCounter));
        mySeriesCounter++;
        Double cdfValue = 100.0;
        for (int i = 0; i < dataPoints.size(); i++)
        {
            pdfSeries.getData().add(new XYChart.Data<Integer, Double>(i, dataPoints.get(i)));
            cdfSeries.getData().add(new XYChart.Data<Integer, Double>(i, cdfValue));
            cdfValue -= dataPoints.get(i);
        }

        if (!myHoldDataCheckBox.isSelected())
        {
            myPDF.getData().clear();
            myCDF.getData().clear();
            mySeriesCounter = 0;

        }

        myPDF.getData().addAll(pdfSeries);
        myCDF.getData().addAll(cdfSeries);
    }

}
