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

import javafx.scene.control.ComboBox;

public class ExtendedComboBox extends ComboBox<String> implements ExtendedNodeInterface {
    private String myVarName;

    public ExtendedComboBox(String varName, String[] values)
    {
        myVarName = varName;
        createComboBox(values);
    }
    public ExtendedComboBox(String varName, String[] values, String promptText)
    {
        myVarName = varName;
        createComboBox(values);
        this.setPromptText(promptText);
    }
    public ExtendedComboBox(String varName, String[] values, boolean setFirst)
    {
        myVarName = varName;
        createComboBox(values);
        this.setPromptText(varName);
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
