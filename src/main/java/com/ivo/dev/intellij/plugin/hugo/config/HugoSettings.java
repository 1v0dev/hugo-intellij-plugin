package com.ivo.dev.intellij.plugin.hugo.config;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "HugoPluginSettings",
        storages = {
                @Storage(StoragePathMacros.WORKSPACE_FILE)
        }
)
public class HugoSettings implements PersistentStateComponent<HugoSettings> {

    private boolean useCustomPath;

    private boolean autoFormatFileName;

    private String customHugoPath;

    private String defaultHugoNewOptions;

    public HugoSettings() {
        useCustomPath = false;
    }

    @Nullable
    @Override
    public HugoSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull HugoSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isUseCustomPath() {
        return useCustomPath;
    }

    public void setUseCustomPath(boolean useCustomPath) {
        this.useCustomPath = useCustomPath;
    }

    public String getCustomHugoPath() {
        return customHugoPath;
    }

    public void setCustomHugoPath(String customHugoPath) {
        this.customHugoPath = customHugoPath;
    }

    public String getDefaultHugoNewOptions() {
        return defaultHugoNewOptions;
    }

    public void setDefaultHugoNewOptions(String defaultHugoNewOptions) {
        this.defaultHugoNewOptions = defaultHugoNewOptions;
    }

    public static HugoSettings getInstance(Project project) {
        return ServiceManager.getService(project, HugoSettings.class);
    }

    public boolean isAutoFormatFileName() {
        return autoFormatFileName;
    }

    public void setAutoFormatFileName(boolean autoFormatFileName) {
        this.autoFormatFileName = autoFormatFileName;
    }
}
