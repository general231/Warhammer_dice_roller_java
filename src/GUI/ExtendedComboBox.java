package GUI;

import javafx.scene.control.ComboBox;

public class ExtendedComboBox extends ComboBox<String> implements ExtendedNodeInterface {
    private String myVarName;

    public ExtendedComboBox(String varName, String[] values)
    {
        myVarName = varName;
        createComboBox(values);
    }
    public ExtendedComboBox(String varName, String[] values, boolean setFirst)
    {
        myVarName = varName;
        createComboBox(values);
        if (setFirst) { this.getSelectionModel().selectFirst(); }
    }

    private void createComboBox(String[] values)
    {
        for (String aValue : values)
        {
            this.getItems().add(aValue);
        }
    }

    public String getData()
    {
        if (this.getValue() != null && !this.getValue().equals("")) {
            return "\"" + myVarName + "\":\"" + this.getValue() + "\",";
        }
        return "";
    }

}
