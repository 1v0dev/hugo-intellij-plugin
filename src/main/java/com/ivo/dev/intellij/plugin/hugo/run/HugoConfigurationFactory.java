package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

import org.jetbrains.annotations.NotNull;

public class HugoConfigurationFactory extends ConfigurationFactory {

    private static final String FACTORY_NAME = "Hugo configuration factory";

    public HugoConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new HugoRunConfiguration(project, this, "Hugo");
    }

    @NotNull
    @Override
    public String getName() {
        return FACTORY_NAME;
    }

    @NotNull
    @Override
    public java.lang.String getId() {
        return FACTORY_NAME;
    }
}
