package com.ivo.dev.intellij.plugin.hugo.action;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vcs.changes.VcsDirtyScopeManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.pom.Navigatable;
import com.ivo.dev.intellij.plugin.hugo.util.HugoCommandUtil;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import javax.swing.*;

public class NewActionDialog extends DialogWrapper {
    private JPanel dialogPanel;
    private JTextField fileNameField;
    private JTextField argumentsField;
    private Project project;
    private VirtualFile vf;

    protected NewActionDialog(@Nullable Project project, VirtualFile vf) {
        super(project);
        this.project = project;
        this.vf = vf;
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
        StringBuilder sb = new StringBuilder("new ");
        sb.append(vf.getPath()).append("/").append(fileNameField.getText());
        if (StringUtils.isNotBlank(argumentsField.getText())) {
            sb.append(" ").append(argumentsField.getText());
        }

        GeneralCommandLine command = HugoCommandUtil.createHugoCommand(false, sb.toString(), project);
        try {
            command.createProcess();

            ApplicationManager.getApplication().runWriteAction(new Runnable() {
                public void run() {
                    vf.refresh(false, true);
                }
            });

            VcsDirtyScopeManager dirtyScopeManager = VcsDirtyScopeManager.getInstance(project);
            dirtyScopeManager.dirDirtyRecursively(vf);
            //TODO fix refresh
        } catch (Exception e) {
            Notification ntf = new Notification("Hugo Notifications", "Hugo New Error",
                    e.getMessage(), NotificationType.ERROR);
            Notifications.Bus.notify(ntf);
        }

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
