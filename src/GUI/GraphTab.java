package GUI;

import StatisticsEngine.StatisticsEngine;
import StatisticsEngine.StatisticsStorageObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GraphTab extends Tab{
    private AreaChart<Integer, Double> myPDF;
    private LineChart<Integer, Double> myCDF;
    private CheckBox myHoldDataCheckBox;
    private Button myViewButton;
    private Button myCopyButton;
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
        myPDF.setPrefHeight(Constants.graphTabHeight);
        myCDF.setPrefHeight(Constants.graphTabHeight);
        myPDF.setPrefWidth(Constants.graphTabWidth);
        myCDF.setPrefWidth(Constants.graphTabWidth);

        StackPane stackPane = new StackPane();
        myViewButton = new Button("View PDF");
        myHoldDataCheckBox = new CheckBox("Hold");
        myCopyButton = new Button("Copy");
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(myViewButton, myCopyButton, myHoldDataCheckBox);
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

        myCopyButton.setOnAction(e -> {
            if (!visibility)
            {
                copyGraphToClipboard(myCDF);
            }
            else
            {
                copyGraphToClipboard(myPDF);
            }
        });
    }

    private void copyGraphToClipboard(Chart chart)
    {
        chart.setAnimated(false);
        WritableImage temp = new WritableImage((int)chart.getWidth(), (int)chart.getHeight());
        WritableImage image = chart.snapshot(new SnapshotParameters(), temp);
        ClipboardContent cc = new ClipboardContent();
        cc.putImage(image);
        Clipboard.getSystemClipboard().setContent(cc);
        chart.setAnimated(true);
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
        if (!myHoldDataCheckBox.isSelected())
        {
            myPDF.getData().clear();
            myCDF.getData().clear();
            mySeriesCounter = 0;
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

        myPDF.getData().addAll(pdfSeries);
        myCDF.getData().addAll(cdfSeries);
    }

}
