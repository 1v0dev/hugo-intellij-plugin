package com.ivo.dev.intellij.plugin.hugo;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class HugoRunConfigurationType implements ConfigurationType {

    @Override
    public String getDisplayName() {
        return "Hugo Run Configuration";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "Run Configuration for Hugo Server";
    }

    @Override
    public Icon getIcon() {
        return AllIcons.General.Information;
    }

    @NotNull
    @Override
    public String getId() {
        return "HUGO_RUN_CONFIGURATION";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[] {
                //new DemoConfigurationFactory(this)
        };
    }

}
