package com.ivo.dev.intellij.plugin.hugo.config;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HugoSettingsConfigurable implements SearchableConfigurable {

    private HugoSettings hugoSettings;
    private HugoSettingsEditor hugoSettingsEditor;
    private Project project;

    public HugoSettingsConfigurable(@NotNull Project project) {
        this.project = project;
        hugoSettings = HugoSettings.getInstance(project);
    }

    @NotNull
    @Override
    public String getId() {
        return "hugo.plugin.settings";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Hugo Plugin";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        hugoSettingsEditor = new HugoSettingsEditor(hugoSettings, project);
        return hugoSettingsEditor.getSettingsPanel();
    }

    @Override
    public boolean isModified() {
        return hugoSettingsEditor.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        hugoSettingsEditor.apply();
    }
}
