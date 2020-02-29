package GUI;

import StatisticsEngine.StatisticsEngine;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        primaryStage.setTitle("Warhammer dice roller");
        Button btn = new Button();
        btn.setText("Calculate");

        VBox root = new VBox();
        SplitPane splitPane = new SplitPane();
        HBox toolbar = new HBox();
        toolbar.setMaxHeight(Constants.toolbarHeight);
        ExtendedComboBox iterationComboBox = new ExtendedComboBox("iterations", Constants.iterations, true);
        Label errorLabel = new Label();
        toolbar.getChildren().addAll(btn, iterationComboBox, new Label("error: "), errorLabel);
        OptionsTabPane options = new OptionsTabPane();
        StatisticsTabPane statistics = new StatisticsTabPane();
        root.setAlignment(Pos.BOTTOM_CENTER );

        btn.setOnAction(e -> {
            String jsonString = options.getData();
            try {
                StatisticsEngine.checkForRequriedFields(jsonString);
                StatisticsEngine statEngine = new StatisticsEngine(jsonString, Constants.stringIterationsToNum(iterationComboBox.getData()));
                statEngine.executeOrder();
                errorLabel.setText("");
            }
            catch (IllegalArgumentException except)
            {
                errorLabel.setText(except.getMessage());
            }
        });

        splitPane.getItems().addAll(options, statistics);
        root.getChildren().addAll(splitPane, toolbar);
        primaryStage.setScene(new Scene(root, Constants.windowWidth, Constants.windowHeight));
        primaryStage.show();
    }
}