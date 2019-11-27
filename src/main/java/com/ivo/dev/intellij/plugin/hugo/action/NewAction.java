package com.ivo.dev.intellij.plugin.hugo.action;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.VcsDirtyScopeManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.ivo.dev.intellij.plugin.hugo.util.HugoCommandUtil;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class NewAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        VirtualFile vf = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE);
        NewActionDTO dto = new NewActionDTO();
        boolean ok = new NewActionDialog(anActionEvent.getProject(), dto).showAndGet();
        if (ok && vf != null) {
            runNewCommand(dto, vf, anActionEvent.getProject());
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getData(CommonDataKeys.NAVIGATABLE) instanceof PsiDirectory);
    }

    private void runNewCommand(NewActionDTO dto, VirtualFile vf, Project project) {
        StringBuilder sb = new StringBuilder("new ");
        sb.append(vf.getPath()).append("/").append(dto.getFileName());
        if (StringUtils.isNotBlank(dto.getArguments())) {
            sb.append(" ").append(dto.getArguments());
        }

        GeneralCommandLine command = HugoCommandUtil.createHugoCommand(false, sb.toString(), project, null);
        try {
            command.createProcess().waitFor(15, TimeUnit.SECONDS);
            vf.refresh(false, true);
        } catch (Exception e) {
            Notification ntf = new Notification("Hugo Notifications", "Hugo New Error",
                    e.getMessage(), NotificationType.ERROR);
            Notifications.Bus.notify(ntf);
        }
    }
}
