package com.ivo.dev.intellij.plugin.hugo.config;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.lang.StringUtils;

public class HugoSettingsEditor {
    private JCheckBox pathToHugoExecutableCheckBox;
    private TextFieldWithBrowseButton customHugoPathField;
    private JPanel settingsPanel;
    private JTextField defaultHugoNewOptionsField;
    private JCheckBox autoFormatFileNameCheckBox;
    private HugoSettings hugoSettings;

    public HugoSettingsEditor(HugoSettings hugoSettings, Project project) {
        this.hugoSettings = hugoSettings;
        pathToHugoExecutableCheckBox.setSelected(hugoSettings.isUseCustomPath());
        autoFormatFileNameCheckBox.setSelected(hugoSettings.isAutoFormatFileName());
        customHugoPathField.setEnabled(hugoSettings.isUseCustomPath());
        if (hugoSettings.getCustomHugoPath() != null && !hugoSettings.getCustomHugoPath().isEmpty()) {
            customHugoPathField.setText(hugoSettings.getCustomHugoPath());
        }

        if (hugoSettings.getDefaultHugoNewOptions() != null && !hugoSettings.getDefaultHugoNewOptions().isEmpty()) {
            defaultHugoNewOptionsField.setText(hugoSettings.getDefaultHugoNewOptions());
        }

        pathToHugoExecutableCheckBox.addActionListener(
            actionEvent -> customHugoPathField.setEnabled(pathToHugoExecutableCheckBox.isSelected()));

        FileChooserDescriptor fcd = FileChooserDescriptorFactory.createSingleFileDescriptor();
        customHugoPathField.addBrowseFolderListener("Select Hugo Executable", null, project, fcd);
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public boolean isModified() {
        return hugoSettings.isUseCustomPath() != pathToHugoExecutableCheckBox.isSelected()
                || !customHugoPathField.getText().equals(hugoSettings.getCustomHugoPath())
                || !defaultHugoNewOptionsField.getText().equals(hugoSettings.getDefaultHugoNewOptions())
                || hugoSettings.isAutoFormatFileName() != autoFormatFileNameCheckBox.isSelected();
    }

    public void apply() throws ConfigurationException {
        if (pathToHugoExecutableCheckBox.isSelected() && customHugoPathField.getText().isEmpty()) {
            throw new ConfigurationException("The path cannot be empty");
        }


        hugoSettings.setUseCustomPath(pathToHugoExecutableCheckBox.isSelected());
        hugoSettings.setCustomHugoPath(customHugoPathField.getText());
        hugoSettings.setDefaultHugoNewOptions(defaultHugoNewOptionsField.getText());
        hugoSettings.setAutoFormatFileName(autoFormatFileNameCheckBox.isSelected());
    }
}
