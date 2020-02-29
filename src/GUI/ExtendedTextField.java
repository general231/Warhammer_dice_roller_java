package GUI;

import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.*;

public class ExtendedTextField extends TextField implements ExtendedNodeInterface {
    private String myVarName;

    public ExtendedTextField(String varName)
    {
        myVarName = varName;
        // this prevents you entering any characters other than numbers
        this.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!"0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
    }

    public String getData()
    {
        if (this.getText() != null) {
            return "\"" + myVarName + "\":" + this.getText() + ",";
        }
        return "";
    }

}
