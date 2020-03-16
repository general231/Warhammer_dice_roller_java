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

import StatisticsEngine.StatisticsEngine;
import StatisticsEngine.StatisticsStorageObject;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GuiMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Warhammer Statistics");
        Button btn = new Button("Calculate");

        Button aboutBtn = new Button("About");
        AboutPopup about = new AboutPopup();
        about.setAutoHide(true);
        aboutBtn.setOnAction( e-> {
            if (!about.isShowing())
            {
                about.show(primaryStage);
            }
        });

        VBox root = new VBox();
        SplitPane splitPane = new SplitPane();
        HBox toolbar = new HBox();
        toolbar.setMaxHeight(Constants.toolbarHeight);
        ExtendedComboBox iterationComboBox = new ExtendedComboBox("iterations", Constants.iterations, true);
        Label errorLabel = new Label();
        toolbar.getChildren().addAll(btn, new Label("Number of Iterations: "), iterationComboBox, aboutBtn, new Label("error: "), errorLabel);
        OptionsTabPane options = new OptionsTabPane();
        StatisticsTabPane statistics = new StatisticsTabPane();
        root.setAlignment(Pos.BOTTOM_CENTER );

        btn.setOnAction(e -> {
            String jsonString = options.getData();
            try {
                StatisticsEngine.checkForRequriedFields(jsonString);
                StatisticsEngine statEngine = new StatisticsEngine(jsonString, Constants.stringIterationsToNum(iterationComboBox.getData()));
                StatisticsStorageObject statObject = statEngine.executeOrder();
                statistics.update(statObject);
                errorLabel.setText("");
            }
            catch (IllegalArgumentException except)
            {
                errorLabel.setText(except.getMessage());
            }
        });

        splitPane.getItems().addAll(options, statistics);
        splitPane.setPrefHeight(Constants.splitpaneHeight);
        root.getChildren().addAll(splitPane, toolbar);
        primaryStage.setScene(new Scene(root, Constants.windowWidth, Constants.windowHeight));
        primaryStage.show();
    }
}