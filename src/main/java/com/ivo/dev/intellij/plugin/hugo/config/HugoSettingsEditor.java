package com.ivo.dev.intellij.plugin.hugo.config;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HugoSettingsEditor {
    private JCheckBox pathToHugoExecutableCheckBox;
    private TextFieldWithBrowseButton customHugoPathField;
    private JPanel settingsPanel;
    private HugoSettings hugoSettings;

    public HugoSettingsEditor(HugoSettings hugoSettings, Project project) {
        this.hugoSettings = hugoSettings;
        pathToHugoExecutableCheckBox.setSelected(hugoSettings.isUseCustomPath());
        customHugoPathField.setEnabled(hugoSettings.isUseCustomPath());
        if (StringUtils.isNotEmpty(hugoSettings.getCustomHugoPath())) {
            customHugoPathField.setText(hugoSettings.getCustomHugoPath());
        }

        pathToHugoExecutableCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                customHugoPathField.setEnabled(pathToHugoExecutableCheckBox.isSelected());
            }
        });

        FileChooserDescriptor fcd = FileChooserDescriptorFactory.createSingleFileDescriptor();
        customHugoPathField.addBrowseFolderListener("Select Hugo Executable", null, project, fcd);
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public boolean isModified() {
        return hugoSettings.isUseCustomPath() != pathToHugoExecutableCheckBox.isSelected()
                || !customHugoPathField.getText().equals(hugoSettings.getCustomHugoPath());
    }

    public void apply() throws ConfigurationException {
        if (pathToHugoExecutableCheckBox.isSelected() && StringUtils.isEmpty(customHugoPathField.getText())) {
            throw new ConfigurationException("The path can not be empty");
        }

        hugoSettings.setUseCustomPath(pathToHugoExecutableCheckBox.isSelected());
        hugoSettings.setCustomHugoPath(customHugoPathField.getText());
    }
}
