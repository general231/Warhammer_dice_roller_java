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

import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import javafx.scene.image.WritableImage;
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
