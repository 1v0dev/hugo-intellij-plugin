package com.ivo.dev.intellij.plugin.hugo.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiDirectory;

import org.jetbrains.annotations.NotNull;

public class NewAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        VirtualFile vf = anActionEvent.getData(CommonDataKeys.VIRTUAL_FILE);
        Navigatable nv = anActionEvent.getData(CommonDataKeys.NAVIGATABLE);
        new NewActionDialog(anActionEvent.getProject(), vf).showAndGet();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getData(CommonDataKeys.NAVIGATABLE) instanceof PsiDirectory);
    }
}
