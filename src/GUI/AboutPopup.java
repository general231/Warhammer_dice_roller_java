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
        Label licence = new Label("Licence: Licenced under the Apache Version 2.0 licence");


        VBox vBox = new VBox(authorLabel, gitRepoistory, licence);
        vBox.setBackground(Background.EMPTY);
        String style = "-fx-background-color: rgba(255, 255, 255, 0.5);";
        vBox.setStyle(style);
        this.getContent().addAll(vBox);
    }
}
