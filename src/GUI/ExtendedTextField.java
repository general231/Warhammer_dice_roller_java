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

import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.*;

public class ExtendedTextField extends TextField implements ExtendedNodeInterface {
    private String myVarName;

    public ExtendedTextField(String varName)
    {
        myVarName = varName;
        this.setMaxWidth(40);
        // this prevents you entering any characters other than numbers
        this.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    public String getData()
    {
        if (!this.getText().equals("")) {
            return "\"" + myVarName + "\":" + this.getText() + ",";
        }
        return "";
    }

}
