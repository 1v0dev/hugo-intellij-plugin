package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class HugoRunSettingsEditor extends SettingsEditor<HugoRunConfiguration> {
    private JPanel myPanel;
    private JTextField argumentsField;

    @Override
    protected void resetEditorFrom(@NotNull HugoRunConfiguration s) {
        s.setArguments(null);
    }

    @Override
    protected void applyEditorTo(@NotNull HugoRunConfiguration s) throws ConfigurationException {
        s.setArguments(argumentsField.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }


}
