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

        VBox root = new VBox();
        SplitPane splitPane = new SplitPane();

        OptionsTabPane options = new OptionsTabPane();
        StatisticsTabPane statistics = new StatisticsTabPane();

        btn.setOnAction(e -> {
            System.out.println(options.getData());
        });
        splitPane.getItems().addAll(options, statistics, btn);
        primaryStage.setScene(new Scene(splitPane, Constants.windowWidth, Constants.windowHeight));
        primaryStage.show();
    }
}