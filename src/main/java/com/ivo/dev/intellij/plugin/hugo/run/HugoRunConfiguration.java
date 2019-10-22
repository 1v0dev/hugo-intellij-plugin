package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;

import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HugoRunConfiguration extends RunConfigurationBase {

    private String arguments;

    protected HugoRunConfiguration(@NotNull Project project,
                                   @Nullable ConfigurationFactory factory,
                                   @Nullable String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new HugoRunSettingsEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {

    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment)
            throws ExecutionException {
        final CommandLineState state = new CommandLineState(executionEnvironment) {

            @NotNull
            @Override
            protected ProcessHandler startProcess() throws ExecutionException {

                GeneralCommandLine commandLine = new GeneralCommandLine();
                commandLine.setExePath("hugo");
                commandLine.addParameter("server");
                if (StringUtils.isNotEmpty(arguments)) {
                    commandLine.addParameter(arguments);
                }
                commandLine.setWorkDirectory(executionEnvironment.getProject().getBasePath());
                commandLine.createProcess();

                OSProcessHandler processHandler = new OSProcessHandler(commandLine);
                processHandler.startNotify();

                return processHandler;

            }

        };

        return state;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

}
