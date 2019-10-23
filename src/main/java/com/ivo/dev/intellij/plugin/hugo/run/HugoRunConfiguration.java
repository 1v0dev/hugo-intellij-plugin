package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;

import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import com.ivo.dev.intellij.plugin.hugo.util.HugoCommandUtil;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HugoRunConfiguration extends RunConfigurationBase {

    private String arguments;
    private boolean runServer = true;
    private final String KEY_ARGUMENTS = "hugo.plugin.arguments";
    private final String KEY_RUN_SERVER = "hugo.plugin.run.server";

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

        return new CommandLineState(executionEnvironment) {

            @NotNull
            @Override
            protected ProcessHandler startProcess() throws ExecutionException {
                OSProcessHandler processHandler = new OSProcessHandler(
                        HugoCommandUtil.createHugoCommand(runServer, arguments, executionEnvironment.getProject()));
                processHandler.startNotify();

                return processHandler;
            }
        };
    }

    @Override
    public void writeExternal(@NotNull Element element) {
        super.writeExternal(element);
        JDOMExternalizerUtil.writeField(element, KEY_ARGUMENTS, arguments);
        JDOMExternalizerUtil.writeField(element, KEY_RUN_SERVER, runServer ? "run" : "no");
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);
        arguments = JDOMExternalizerUtil.readField(element, KEY_ARGUMENTS);
        runServer = "run".equals(JDOMExternalizerUtil.readField(element, KEY_RUN_SERVER));
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public boolean isRunServer() {
        return runServer;
    }

    public void setRunServer(boolean runServer) {
        this.runServer = runServer;
    }
}
