package com.ivo.dev.intellij.plugin.hugo.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public class HugoRunConfigurationType implements ConfigurationType {

    @NotNull
    @Override
    public String getDisplayName() {
        return "Hugo";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "Run Configuration for Hugo Server";
    }

    @Override
    public Icon getIcon() {
        //return AllIcons.General.Information;
        return IconLoader.getIcon("/images/hugo-icon-15.png");
    }

    @NotNull
    @Override
    public String getId() {
        return "HUGO_RUN_CONFIGURATION";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[] {
                new HugoConfigurationFactory(this)
        };
    }

}
