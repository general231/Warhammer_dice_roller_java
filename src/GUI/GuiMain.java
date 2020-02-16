package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        btn.setOnAction(e -> {
            System.out.println("Hi");
                });

        SplitPane root = new SplitPane();

        OptionsTabPane options = new OptionsTabPane();
        StatisticsTabPane statistics = new StatisticsTabPane();
        root.getItems().addAll(options, statistics);

        primaryStage.setScene(new Scene(root, Constants.windowWidth, Constants.windowHeight));
        primaryStage.show();
    }
}