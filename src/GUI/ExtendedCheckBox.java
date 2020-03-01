package GUI;

import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyEvent;

public class ExtendedCheckBox extends CheckBox implements ExtendedNodeInterface {
    private String myVarName;

    public ExtendedCheckBox(String displayName, String varName)
    {
        myVarName = varName;
        this.setText(displayName);
    }

    public String getData()
    {
        return "\"" + myVarName + "\":" + this.isSelected() + ",";
    }
}
