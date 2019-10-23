package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class HugoRunSettingsEditor extends SettingsEditor<HugoRunConfiguration> {
    private JPanel myPanel;
    private JTextField argumentsField;
    private JRadioButton hugoServerRadioButton;
    private JRadioButton hugoRadioButton;

    @Override
    protected void resetEditorFrom(@NotNull HugoRunConfiguration s) {
        argumentsField.setText(s.getArguments());
        if (s.isRunServer()) {
            hugoServerRadioButton.setSelected(true);
        } else {
            hugoRadioButton.setSelected(true);
        }
    }

    @Override
    protected void applyEditorTo(@NotNull HugoRunConfiguration s) throws ConfigurationException {
        s.setArguments(argumentsField.getText());
        s.setRunServer(hugoServerRadioButton.isSelected());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }




}
