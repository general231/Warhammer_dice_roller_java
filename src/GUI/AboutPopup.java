package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class AboutPopup extends Popup {
    public AboutPopup()
    {
        Label authorLabel = new Label("Creater: J Marks");
        Label gitRepoistory = new Label("Github: https://github.com/general231/Warhammer_dice_roller_java");


        VBox vBox = new VBox(authorLabel, gitRepoistory);
        vBox.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
        vBox.setStyle(style);
        this.getContent().addAll(vBox);
    }
}
