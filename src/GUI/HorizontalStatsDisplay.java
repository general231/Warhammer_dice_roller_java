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

import javafx.scene.layout.HBox;

import javafx.scene.control.Label;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.text.DecimalFormat;

public class HorizontalStatsDisplay extends HBox {
    private Label myMaxBox;
    private Label myMinBox;
    private Label myMeanBox;
    private Label myStdDevBox;
    private DecimalFormat myFormat;

    public HorizontalStatsDisplay(String displayName)
    {
        myMinBox = new Label("0");
        myMaxBox = new Label("0");
        myMeanBox = new Label("0");
        myStdDevBox = new Label("0");
        myFormat = new DecimalFormat("#.00");
        this.getChildren().add(new Label(displayName));
        this.getChildren().add(new Label("    Max: "));
        this.getChildren().add(myMaxBox);
        this.getChildren().add(new Label("    Min: "));
        this.getChildren().add(myMinBox);
        this.getChildren().add(new Label("    Average(Mean): "));
        this.getChildren().add(myMeanBox);
        this.getChildren().add(new Label("    Standard Deviation: "));
        this.getChildren().add(myStdDevBox);
    }

    public void updateValues(DescriptiveStatistics stats)
    {
        myMinBox.setText(myFormat.format(stats.getMin()));
        myMaxBox.setText(myFormat.format(stats.getMax()));
        myMeanBox.setText(myFormat.format(stats.getMean()));
        myStdDevBox.setText(myFormat.format(stats.getStandardDeviation()));
    }
}
