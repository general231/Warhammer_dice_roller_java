package GUI;

import javafx.scene.control.ComboBox;

public class ExtendedComboBox extends ComboBox {
    private String myVarName;

    public ExtendedComboBox(String varName, int[] values)
    {

    }

    public ExtendedComboBox(String varName, String[] values)
    {

    }


    private void createComboBox(int[] values)
    {
        for (int num : values)
        {
            this.getItems().add(num);
        }
    }

    private ComboBox<String> createComboBox(String[] values)
    {
        ComboBox<String> cBox = new ComboBox<String>();
        for (String string : values)
        {
            cBox.getItems().add(string);
        }
        return cBox;
    }
}
