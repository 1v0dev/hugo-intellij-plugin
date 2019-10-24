package com.ivo.dev.intellij.plugin.hugo.action;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewActionDialog extends DialogWrapper {
    private JPanel dialogPanel;
    private JTextField fileNameField;
    private JTextField argumentsField;
    private NewActionDTO dto;

    protected NewActionDialog(@Nullable Project project, NewActionDTO dto) {
        super(project);
        this.dto = dto;
        init();
        setTitle("Hugo New");
        setAutoAdjustable(true);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return dialogPanel;
    }

    @Override
    protected void doOKAction() {
        dto.setFileName(fileNameField.getText());
        dto.setArguments(argumentsField.getText());
        super.doOKAction();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if (StringUtils.isEmpty(fileNameField.getText())) {
            return new ValidationInfo("File name can not be empty", fileNameField);
        }
        return null;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return fileNameField;
    }
}
