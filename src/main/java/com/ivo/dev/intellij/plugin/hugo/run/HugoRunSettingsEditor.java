package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class HugoRunSettingsEditor extends SettingsEditor<HugoRunConfiguration> {
    private JPanel myPanel;
    private JTextField argumentsField;
    private JRadioButton hugoServerRadioButton;
    private JRadioButton hugoRadioButton;
    private TextFieldWithBrowseButton customProjectDir;

    @Override
    protected void resetEditorFrom(@NotNull HugoRunConfiguration s) {
        argumentsField.setText(s.getArguments());
        if (s.isRunServer()) {
            hugoServerRadioButton.setSelected(true);
        } else {
            hugoRadioButton.setSelected(true);
        }

        FileChooserDescriptor fcd = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        customProjectDir.addBrowseFolderListener("Select Hugo Project Directory",
                null, s.getProject(), fcd);

        if (StringUtils.isNotEmpty(s.getCustomProjectDir())) {
            customProjectDir.setText(s.getCustomProjectDir());
        }
    }

    @Override
    protected void applyEditorTo(@NotNull HugoRunConfiguration s) throws ConfigurationException {
        s.setArguments(argumentsField.getText());
        s.setRunServer(hugoServerRadioButton.isSelected());
        s.setCustomProjectDir(customProjectDir.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }




}
