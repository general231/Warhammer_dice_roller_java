package GUI;

import javafx.scene.control.TextField;

public class ExtendedTextField extends TextField implements ExtendedNodeInterface {
    private String myVarName;

    public ExtendedTextField(String varName)
    {
        myVarName = varName;
    }

    public String getData()
    {
        if (this.getText() != null) {
            return "\"" + myVarName + "\":\"" + this.getText() + "\",";
        }
        return "";
    }

}
