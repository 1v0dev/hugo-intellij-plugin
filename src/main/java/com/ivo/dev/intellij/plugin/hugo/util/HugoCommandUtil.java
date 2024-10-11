package com.ivo.dev.intellij.plugin.hugo.util;

import java.nio.file.Path;

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

        if (workingDir != null && !workingDir.isEmpty()) {
            commandLine.setWorkDirectory(resolveWorkingDir(project, workingDir));
        } else {
            commandLine.setWorkDirectory(project.getBasePath());
        }

        if (runServer) {
            commandLine.addParameter("server");
        }

        if (arguments != null && !arguments.isEmpty()) {
            commandLine.addParameters(arguments.trim().split("\\s+"));
        }

        return commandLine;
    }

    private static String resolveWorkingDir(Project project, String workingDir) {
        String projectBasePath = project.getBasePath();
        if (projectBasePath == null) {
            return workingDir;
        }

        Path workingDirPath = Path.of(workingDir);
        if (workingDirPath.isAbsolute()) {
            return workingDir;
        }

        return Path.of(projectBasePath)
            .resolve(workingDirPath)
            .toAbsolutePath()
            .toString();
    }

}
