package com.ivo.dev.intellij.plugin.hugo.util;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.project.Project;
import com.ivo.dev.intellij.plugin.hugo.config.HugoSettings;

import org.apache.commons.lang.StringUtils;

public class HugoCommandUtil {

    public static GeneralCommandLine createHugoCommand(boolean runServer, String arguments, Project project,
                                                       String workingDir) {
        HugoSettings hugoSettings = HugoSettings.getInstance(project);

        GeneralCommandLine commandLine = new GeneralCommandLine();
        if (hugoSettings.isUseCustomPath()) {
            commandLine.setExePath(hugoSettings.getCustomHugoPath());
        } else {
            commandLine.setExePath("hugo");
        }

        if (StringUtils.isNotEmpty(workingDir)) {
            commandLine.setWorkDirectory(workingDir);
        } else {
            commandLine.setWorkDirectory(project.getBasePath());
        }

        if (runServer) {
            commandLine.addParameter("server");
        }

        if (StringUtils.isNotEmpty(arguments)) {
            commandLine.addParameters(arguments.trim().split("\\s+"));
        }

        return commandLine;
    }

}
